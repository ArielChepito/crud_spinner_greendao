package com.example.ariel.bddtaller2.plate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ariel.bddtaller2.R;

import java.util.List;

public class PlateActivity extends AppCompatActivity {

    private RecyclerView myReclyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    PlateController PlateController;
    List<Plate> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate);

        try{
            PlateController = new PlateController();
            lista = PlateController.getAll();
            myReclyclerView = (RecyclerView) findViewById(R.id.RecyclerPlate);
            myAdapter = new PlateAdapter( lista, R.layout.plate_item,new PlateAdapter.onItemClickListener(){
                @Override
                public void onItemClick(final Plate Plate, int position) {
                    PlateDialog.showDialog(
                            PlateActivity.this,
                            Plate,
                            "Modificar",
                            "Datos: ",
                            R.drawable.ic_launcher_background,
                            "Modificar",
                            "Eliminar",
                            "Cancelar",
                            false,
                            new PlateDialog.onDataReadyListener() {
                                @Override
                                public void onDataReady(Plate Plate, int opt) {
                                    PlateActivity.this.crud(opt,Plate);
                                }
                            });
                }
            });
            //todos los tipos de layout manager con los que se puede jugar con el recycler view
            myLayoutManager = new LinearLayoutManager(this);
            //myLayoutManager = new GridLayoutManager(this,2);
            // myLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            //solo en caso que sepamos que el tamaño del layout no va a cambiar ahrehgamos esto e incrementa el rendimiento, aunque de nada sirve con el StraggeredGridLayout porque cambia los tamaños
            myReclyclerView.setHasFixedSize(true);
            //animacion por defecto
            myReclyclerView.setItemAnimator(new DefaultItemAnimator());
            myReclyclerView.setLayoutManager(myLayoutManager);
            myReclyclerView.setAdapter(myAdapter);
        }catch(Exception e)
        {
            Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
            System.out.println("Error :" + e.getMessage());
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.plate_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_plate_crear:
                final Plate Plate = new Plate();
                PlateDialog.showDialog(
                        PlateActivity.this,
                        Plate,
                        "Guardar",
                        "Datos: ",
                        R.drawable.ic_launcher_background,
                        "Guardar",
                        "",
                        "Cancelar",
                        false,
                        new PlateDialog.onDataReadyListener() {
                            @Override
                            public void onDataReady(Plate Plate, int opt) {
                                PlateActivity.this.crud(opt,Plate);
                            }
                        });

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void crud(int op, Plate Plate)
    {
        switch (op)
        {
            case 1:
                this.PlateController.save(Plate);
                break;
            case 2:
                this.PlateController.update(Plate);
                break;
            case 3:
                this.PlateController.delete(Plate);
        }

        lista.clear();
        lista.addAll(PlateController.getAll());
        this.myAdapter.notifyDataSetChanged();
    }

}
