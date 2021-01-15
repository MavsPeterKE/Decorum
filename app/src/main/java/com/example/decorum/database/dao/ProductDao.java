package com.example.decorum.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.decorum.database.entity.User;

@Dao
public abstract class ProductDao extends BaseDao<User> {

    @Query("SELECT * FROM products_table")
    public abstract LiveData<User> getAllProducts();

    @Query("SELECT *FROM products_table WHERE favourite_flag=1")
    public abstract LiveData<User> getAllFavouriteProducts();
}
