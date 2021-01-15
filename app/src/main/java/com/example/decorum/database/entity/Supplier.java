package com.example.decorum.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "supplier_table")
public class Supplier {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "supplier_id")
    private String supplierId;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "phone_number")
    private long productDescription;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "latitude")
    private String  latitude;

    @ColumnInfo(name = "longitude")
    private String  longitue;

    public Supplier() {
    }

    @NonNull
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(@NonNull String supplierId) {
        this.supplierId = supplierId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(long productDescription) {
        this.productDescription = productDescription;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitue() {
        return longitue;
    }

    public void setLongitue(String longitue) {
        this.longitue = longitue;
    }
}
