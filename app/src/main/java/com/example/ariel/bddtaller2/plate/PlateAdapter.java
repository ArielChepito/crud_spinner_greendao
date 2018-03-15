package com.example.ariel.bddtaller2.plate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ariel.bddtaller2.R;


import java.util.List;

/**
 * Created by Ariel on 14/03/2018.
 */

public class PlateAdapter  extends RecyclerView.Adapter<PlateAdapter.ViewHolder>{

    private List<Plate> PlateList;
    private int layout;
    private PlateAdapter.onItemClickListener listener;

    public PlateAdapter(List<Plate> Plate, int layout, PlateAdapter.onItemClickListener listener) {
        this.PlateList = Plate;
        this.layout = layout;
        this.listener = listener;
    }

    @Override
    public PlateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //inlfamos el layout y le pasamos lso datos al constructor del view holder
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        PlateAdapter.ViewHolder vh = new PlateAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PlateAdapter.ViewHolder holder, int position) {

        //llamamos al metodo bind del viewholder pasandole el objdeto y un listener
        holder.bind(PlateList.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return PlateList.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        //Elementos UI a rellenar
        public TextView name;
        public TextView description;
        public Button prompPurpose;

        public ViewHolder(View v){

            //recibe la vista completa para que la rendericemos, pasamos parametros a constructor padre
            // aqui tambien manejamos los datos de logioca para extraer datos y hacer referencias con los elementoss
            super(v);
            this.name =(TextView) v.findViewById(R.id.PlateName);
            this.description = (TextView) v.findViewById(R.id.PlateDesc);
            this.prompPurpose = v.findViewById(R.id.prompPlate);

        }

        public void bind(final Plate Plate, final PlateAdapter.onItemClickListener listener){
            //procesamos los datos para renderizar

            name.setText(Plate.getName());
            if(Plate.getCategory() != null)
            {
                description.setText(Plate.getCategory().getName());
            }

            // this.textViewPlate.setText(Plate.getName());
            /// definimos que por cada elemento del recycler view tenemos un listener que se va a comportart de la siguiente manera

            prompPurpose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(Plate, getAdapterPosition());
                }
            });

        }

    }
    ///declaramos las interfaces con los metodos a implementar
    public interface onItemClickListener{
        void onItemClick(Plate Plate, int position);
    }
}