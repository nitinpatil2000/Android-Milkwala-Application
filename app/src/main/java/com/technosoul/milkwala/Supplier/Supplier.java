package com.technosoul.milkwala.Supplier;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "supplier")
public class Supplier {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "supplierName")
    private String supplierName;

//    String supplierSubText;


    public Supplier(int id, String supplierName) {
        this.id = id;
        this.supplierName = supplierName;
    }

    @Ignore
    public Supplier(String supplierName) {
        this.supplierName = supplierName;
    }

    public Supplier() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}