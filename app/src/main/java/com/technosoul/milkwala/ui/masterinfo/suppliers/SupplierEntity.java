package com.technosoul.milkwala.ui.masterinfo.suppliers;


public class SupplierEntity {
    private int supplierId;
    private String supplierName;
    private String supplierAddress;
    private String supplierNumber;
    private String supplierAltNumber;

    public SupplierEntity(int supplierId, String supplierName, String supplierAddress, String  supplierNumber, String supplierAltNumber) {
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierNumber = supplierNumber;
        this.supplierAltNumber = supplierAltNumber;
    }

    public SupplierEntity() {
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public void setSupplierNumber(String  supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String  getSupplierAltNumber() {
        return supplierAltNumber;
    }

    public void setSupplierAltNumber(String  supplierAltNumber) {
        this.supplierAltNumber = supplierAltNumber;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "SupplierEntity{" +
                "supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", supplierAddress='" + supplierAddress + '\'' +
                ", supplierNumber='" + supplierNumber + '\'' +
                ", supplierAltNumber='" + supplierAltNumber + '\'' +
                '}';
    }
}
