package com.example.decorum.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "products_table")
public class Product {
    @NonNull
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "product_name")
    private String productName;

    @ColumnInfo(name = "unit_price")
    private String unitPrice;

    @ColumnInfo(name = "product_description")
    private long productDescription;

    @ColumnInfo(name = "favourite_flag")
    private boolean favouriteFlag;

    @ColumnInfo(name = "product_rating")
    private String product_rating;

    @ColumnInfo(name = "supplier_id")
    private int supplierId;

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public long getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(long productDescription) {
        this.productDescription = productDescription;
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

    public boolean isFavouriteFlag() {
        return favouriteFlag;
    }

    public void setFavouriteFlag(boolean favouriteFlag) {
        this.favouriteFlag = favouriteFlag;
    }
}
