package com.example.ariel.bddtaller2.plate;

import com.example.ariel.bddtaller2.category.Category;
import com.example.ariel.bddtaller2.plate.Plate;

import java.util.List;

/**
 * Created by Ariel on 14/03/2018.
 */

public interface PlateOperations {
    boolean save(Plate Category);
    boolean update(Plate Category);
    boolean delete(Plate Category);
    List<Plate> getAll();
    List<Plate> getByCategory(Category category);
    Plate get(Long ID);
}
