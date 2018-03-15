package com.example.ariel.bddtaller2.plate;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ariel.bddtaller2.R;
import com.example.ariel.bddtaller2.category.Category;
import com.example.ariel.bddtaller2.category.CategoryController;


/**
 * Created by Ariel on 14/03/2018.
 */

public class PlateDialog {
    private static Plate Plate;
    private PlateDialog.onDataReadyListener listener;
    static int saveOrUpdate;

    public static void  showDialog(final Context context, final Plate PlateTemp, String title, String descripcion, int iconId
            , String positive, String eliminar, String cancel, boolean cancelable, final PlateDialog.onDataReadyListener listener)
    {

        // inflate alert dialog xml
        LayoutInflater li = LayoutInflater.from(context);
        final View dialogView = li.inflate( R.layout.plate_form, null);

        final TextView lblDescription=dialogView.findViewById(R.id.plateDialogDescription);
        lblDescription.setText(descripcion);

        //gets controlls
        final EditText txtname = (EditText) dialogView
                .findViewById(R.id.plateFormName);


        //get the spinner from the xml.
        Spinner dropdown = dialogView.findViewById(R.id.plateSpinner);

    //create an adapter to describe how the items are displayed, adapters are used in several places in android.
    //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(dialogView.getContext(), android.R.layout.simple_spinner_dropdown_item, (new CategoryController()).getAll());
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);


        txtname.setText(PlateTemp.getName());




        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        // set title
        alertDialogBuilder.setTitle(title);
        // set custom dialog icon
        alertDialogBuilder.setIcon(iconId);
        // set custom_dialog.xml to alertdialog builder
        alertDialogBuilder.setView(dialogView);
        // set dialog message
        Plate=PlateTemp;
        saveOrUpdate = 1;
        if(PlateTemp.getName()!=null){
            saveOrUpdate = 2 ;
        }
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category  category = (Category) parent.getItemAtPosition(position);
                Plate.setCategory(category);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        alertDialogBuilder
                .setCancelable(cancelable)
                .setPositiveButton(positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Plate.setName(txtname.getText().toString());

                        listener.onDataReady(Plate,saveOrUpdate);
                    }
                })
                .setNegativeButton(eliminar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onDataReady(Plate,3);
                    }

                })
                .setNeutralButton(cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
        ;

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }

    public interface onDataReadyListener{
        void onDataReady (Plate Plate, int opt);
    }
}
