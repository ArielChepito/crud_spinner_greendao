package com.example.ariel.bddtaller2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ariel.bddtaller2.category.Category;
import com.example.ariel.bddtaller2.category.CategoryActivity;
import com.example.ariel.bddtaller2.category.CategoryController;
import com.example.ariel.bddtaller2.plate.Plate;
import com.example.ariel.bddtaller2.plate.PlateActivity;
import com.example.ariel.bddtaller2.plate.PlateAdapter;
import com.example.ariel.bddtaller2.plate.PlateController;
import com.example.ariel.bddtaller2.plate.PlateDialog;

import java.util.List;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView myReclyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager myLayoutManager;
    PlateController PlateController;
    List<Plate> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinnerPrincipal);

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, (new CategoryController()).getAll());
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        try{
            PlateController = new PlateController();
            lista = PlateController.getAll();
            myReclyclerView = (RecyclerView) findViewById(R.id.recyclerPrincipal);
            myAdapter = new PlateAdapter( lista, R.layout.principal_item,new PlateAdapter.onItemClickListener(){
                @Override
                public void onItemClick(final Plate Plate, int position) {
                    PlateDialog.showDialog(
                            Principal.this,
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
                                   // Principal.this.crud(opt,Plate);
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

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category  category = (Category) parent.getItemAtPosition(position);
                ////Plate.setCategory(category);
                lista.clear();
                lista.addAll(PlateController.getByCategory(category));
                myAdapter.notifyDataSetChanged();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        lista.clear();
        lista.addAll(PlateController.getAll());
        this.myAdapter.notifyDataSetChanged();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(this, Principal.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, PlateActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, CategoryActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
