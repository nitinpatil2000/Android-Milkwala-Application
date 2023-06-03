package com.technosoul.milkwala.ui.masterinfo.suppliers;


public class SupplierFromServer {
    private int supplierId;
    private String supplierName;
    private String supplierEmail;
    private String supplierAddress;
    private Long supplierNumber;
    private Long supplierAltNumber;



    public SupplierFromServer() {
    }

    public SupplierFromServer(int supplierId, String supplierName,String supplierEmail, String supplierAddress, Long supplierNumber, Long supplierAltNumber) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
        this.supplierAddress = supplierAddress;
        this.supplierNumber = supplierNumber;
        this.supplierAltNumber = supplierAltNumber;

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

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public Long getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(Long supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public Long getSupplierAltNumber() {
        return supplierAltNumber;
    }

    public void setSupplierAltNumber(Long supplierAltNumber) {
        this.supplierAltNumber = supplierAltNumber;
    }

    @Override
    public String toString() {
        return "SupplierFromServer{" +
                "supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", supplierEmail='" + supplierEmail + '\'' +
                ", supplierAddress='" + supplierAddress + '\'' +
                ", supplierNumber=" + supplierNumber +
                ", supplierAltNumber=" + supplierAltNumber +
                '}';
    }
}