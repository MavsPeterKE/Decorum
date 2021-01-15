package com.example.decorum.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "users_table")
public class User {
    @NonNull
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "username")
    private String userName;

    @ColumnInfo(name = "pass")
    private String pass;

    @ColumnInfo(name = "email")
    private long email;

    @ColumnInfo(name = "product_rating")
    private String product_rating;

    @ColumnInfo(name = "supplier_id")
    private int supplierId;

    public User() {
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public long getEmail() {
        return email;
    }

    public void setEmail(long email) {
        this.email = email;
    }

    public String getProduct_rating() {
        return product_rating;
    }

    public void setProduct_rating(String product_rating) {
        this.product_rating = product_rating;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}
