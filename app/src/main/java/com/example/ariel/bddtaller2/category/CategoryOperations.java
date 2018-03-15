package com.example.ariel.bddtaller2.category;

import java.util.List;

/**
 * Created by Ariel on 14/03/2018.
 */

public interface CategoryOperations {
    boolean save(Category Category);
    boolean update(Category Category);
    boolean delete(Category Category);
    List<Category> getAll();
    Category get(Long ID);
}
