package com.example.ariel.bddtaller2.category;

import com.example.ariel.bddtaller2.Utils.Core;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Ariel on 14/03/2018.
 */

public class CategoryController implements CategoryOperations {

    DaoSession daoSession; ///Objeto de la sesion
    CategoryDao categoryDao;  //notasDao sera quien maneje las operaciones de la tabla


    public CategoryController(){
        try{
            ///  Core.getInstance().DeleteAllBases(); //ESTO BORRA TODOS LOS DATOS DE LA BASE, QUITAR!!!!!!!
            daoSession = Core.getInstance().getDaoSession(); //Se recupera la sesion del singleton
            categoryDao = daoSession.getCategoryDao(); //se recupera el manejador de la sesion
        /*Aquí inserto una lista de datos en la base de datos*/
        }
        catch(Exception e)
        {
            System.out.println("Error :" + e.getMessage());
        }



    }

    @Override
    public boolean save(Category Category) {
        try{
            categoryDao.save(Category);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean update(Category Category) {
        try{
            categoryDao.update(Category);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean delete(Category Category) {
        try{
            categoryDao.delete(Category);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.loadAll();
    }

    @Override
    public Category get(Long ID) {
        return categoryDao.load(ID);
    }
}
