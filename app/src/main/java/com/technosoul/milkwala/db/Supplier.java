package com.technosoul.milkwala.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "supplier")
public class Supplier {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "supplier_id")
    private int supplierId;

    @ColumnInfo(name = "supplier_name")
    private String supplierName;

    @ColumnInfo(name = "supplier_address")
    private String supplierAddress;

    @ColumnInfo(name = "supplier_number")
    private String supplierNumber;

    @ColumnInfo(name="supplier_alt_number")
    private String supplierAltNumber;

    public Supplier(int supplierId, String supplierName, String supplierAddress, String supplierNumber, String supplierAltNumber) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierNumber = supplierNumber;
        this.supplierAltNumber = supplierAltNumber;
    }

    @Ignore
    public Supplier(String supplierName, String supplierAddress, String supplierNumber, String supplierAltNumber) {
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierNumber = supplierNumber;
        this.supplierAltNumber = supplierAltNumber;
    }

    public Supplier() {
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAltNumber() {
        return supplierAltNumber;
    }

    public void setSupplierAltNumber(String supplierAltNumber) {
        this.supplierAltNumber = supplierAltNumber;
    }
}