package com.example.ariel.bddtaller2.category;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ariel.bddtaller2.R;

/**
 * Created by Ariel on 14/03/2018.
 */

public class CategoryDialog {

    private static Category Category;
    private onDataReadyListener listener;
    static int saveOrUpdate;

    public static void  showDialog(final Context context, final Category CategoryTemp, String title, String descripcion, int iconId
            , String positive, String eliminar, String cancel, boolean cancelable, final onDataReadyListener listener)
    {

        // inflate alert dialog xml
        LayoutInflater li = LayoutInflater.from(context);
        final View dialogView = li.inflate( R.layout.category_form, null);

        final TextView lblDescription=dialogView.findViewById(R.id.categoryDialogDescription);
        lblDescription.setText(descripcion);

        //gets controlls
        final EditText txtname = (EditText) dialogView
                .findViewById(R.id.categoryFormName);


        txtname.setText(CategoryTemp.getName());


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        // set title
        alertDialogBuilder.setTitle(title);
        // set custom dialog icon
        alertDialogBuilder.setIcon(iconId);
        // set custom_dialog.xml to alertdialog builder
        alertDialogBuilder.setView(dialogView);
        // set dialog message
        Category=CategoryTemp;
        saveOrUpdate = 1;
        if(CategoryTemp.getName()!=null){
            saveOrUpdate = 2 ;
        }
        alertDialogBuilder
                .setCancelable(cancelable)
                .setPositiveButton(positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Category.setName(txtname.getText().toString());

                        listener.onDataReady(Category,saveOrUpdate);
                    }
                })
                .setNegativeButton(eliminar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onDataReady(Category,3);
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
        void onDataReady (Category Category, int opt);
    }
}
