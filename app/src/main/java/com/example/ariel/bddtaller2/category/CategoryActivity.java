package com.example.ariel.bddtaller2.category;


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

public class CategoryActivity extends AppCompatActivity {
    
    private RecyclerView myReclyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    CategoryController CategoryController;
    List<Category> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        try{
            CategoryController = new CategoryController();
            lista = CategoryController.getAll();
            myReclyclerView = (RecyclerView) findViewById(R.id.RecyclerCategory);
            myAdapter = new CategoryAdapter( lista, R.layout.category_item,new CategoryAdapter.onItemClickListener(){
                @Override
                public void onItemClick(final Category category, int position) {
                    CategoryDialog.showDialog(
                            CategoryActivity.this,
                            category,
                            "Modificar",
                            "Datos: ",
                            R.drawable.ic_launcher_background,
                            "Modificar",
                            "Eliminar",
                            "Cancelar",
                            false,
                            new CategoryDialog.onDataReadyListener() {
                                @Override
                                public void onDataReady(Category Category, int opt) {
                                    CategoryActivity.this.crud(opt,category);
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
        inflater.inflate(R.menu.category_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_category_crear:
                final Category category = new Category();
                CategoryDialog.showDialog(
                        CategoryActivity.this,
                        category,
                        "Guardar",
                        "Datos: ",
                        R.drawable.ic_launcher_background,
                        "Guardar",
                        "",
                        "Cancelar",
                        false,
                        new CategoryDialog.onDataReadyListener() {
                            @Override
                            public void onDataReady(Category Category, int opt) {
                                CategoryActivity.this.crud(opt,category);
                            }
                        });

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void crud(int op, Category Category)
    {
        switch (op)
        {
            case 1:
                this.CategoryController.save(Category);
                break;
            case 2:
                this.CategoryController.update(Category);
                break;
            case 3:
                this.CategoryController.delete(Category);
        }

        lista.clear();
        lista.addAll(CategoryController.getAll());
        this.myAdapter.notifyDataSetChanged();
    }
}
