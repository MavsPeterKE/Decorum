package com.example.decorum.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.decorum.database.converters.DateConverter;
import com.example.decorum.database.entity.Product;
import com.example.decorum.database.entity.Supplier;
import com.example.decorum.database.entity.User;

import static com.example.decorum.utils.Constants.DB_VERSION;

@Database(entities = {Product.class, Supplier.class, User.class},
        version = DB_VERSION)
@TypeConverters(DateConverter.class)
public abstract class AppDataBase extends RoomDatabase {

    /*public abstract UserDao userDao();*/

}
