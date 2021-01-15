package com.example.decorum.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.decorum.database.entity.User;

@Dao
public abstract class UserDao extends BaseDao<User> {

    @Query("SELECT * FROM users_table")
    public abstract LiveData<User> getUsers();

    @Query("SELECT * from users_table WHERE username LIKE :username AND pass LIKE :password")
    public abstract User getUserByLoginAndPassword(String username, String password);
}
