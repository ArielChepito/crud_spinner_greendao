package com.example.ariel.bddtaller2.category;

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

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    private List<Category> CategoryList;
    private int layout;
    private onItemClickListener listener;

    public CategoryAdapter(List<Category> Category, int layout, onItemClickListener listener) {
        this.CategoryList = Category;
        this.layout = layout;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //inlfamos el layout y le pasamos lso datos al constructor del view holder
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //llamamos al metodo bind del viewholder pasandole el objdeto y un listener
        holder.bind(CategoryList.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return CategoryList.size();
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
            this.name =(TextView) v.findViewById(R.id.categoryName);
            this.prompPurpose = v.findViewById(R.id.prompcategory);

        }

        public void bind(final Category Category, final  onItemClickListener listener){
            //procesamos los datos para renderizar

            name.setText(Category.getName());
            // this.textViewCategory.setText(Category.getName());
            /// definimos que por cada elemento del recycler view tenemos un listener que se va a comportart de la siguiente manera

            prompPurpose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(Category, getAdapterPosition());
                }
            });

        }

    }
    ///declaramos las interfaces con los metodos a implementar
    public interface onItemClickListener{
        void onItemClick(Category Category, int position);
    }
}
