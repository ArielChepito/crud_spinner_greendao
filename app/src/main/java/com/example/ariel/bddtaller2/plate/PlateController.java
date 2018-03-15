package com.example.ariel.bddtaller2.plate;

import com.example.ariel.bddtaller2.Utils.Core;
import com.example.ariel.bddtaller2.category.Category;
import com.example.ariel.bddtaller2.category.DaoSession;


import java.util.List;

/**
 * Created by Ariel on 14/03/2018.
 */

public class PlateController implements PlateOperations {

    DaoSession daoSession; ///Objeto de la sesion
    PlateDao plateDao;  //notasDao sera quien maneje las operaciones de la tabla


    public PlateController(){
        try{
            ///  Core.getInstance().DeleteAllBases(); //ESTO BORRA TODOS LOS DATOS DE LA BASE, QUITAR!!!!!!!
            daoSession = Core.getInstance().getDaoSession(); //Se recupera la sesion del singleton
            plateDao = daoSession.getPlateDao(); //se recupera el manejador de la sesion
        /*Aquí inserto una lista de datos en la base de datos*/
        }
        catch(Exception e)
        {
            System.out.println("Error :" + e.getMessage());
        }



    }
   
   
    @Override
    public boolean save(Plate Category) {
        try{
            plateDao.save(Category);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean update(Plate Category) {
        try{
            plateDao.update(Category);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean delete(Plate Category) {
        try{
            plateDao.delete(Category);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public List<Plate> getAll() {
        return plateDao.loadAll();
    }

    @Override
    public List<Plate> getByCategory(Category category) {
        return plateDao.queryBuilder().where(PlateDao.Properties.CategoryId.eq(category.getId())).list();
    }

    @Override
    public Plate get(Long ID) {
        return plateDao.load(ID);
    }
}
