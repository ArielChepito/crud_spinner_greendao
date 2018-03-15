package com.example.ariel.bddtaller2.category;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Ariel on 14/03/2018.
 */
@Entity
public class Category {

    @Id(autoincrement = true)
    private Long Id;

    private String name;

    @Generated(hash = 1998424035)
    public Category(Long Id, String name) {
        this.Id = Id;
        this.name = name;
    }

    @Generated(hash = 1150634039)
    public Category() {
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
