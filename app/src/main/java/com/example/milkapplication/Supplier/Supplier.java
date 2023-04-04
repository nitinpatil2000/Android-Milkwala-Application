package com.example.milkapplication.Supplier;

public class Supplier {
    int id;
    String supplierTxt;
//    String supplierSubText;



    public Supplier(String supplierTxt) {
        this.supplierTxt = supplierTxt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplierTxt() {
        return supplierTxt;
    }

    public void setSupplierTxt(String supplierTxt) {
        this.supplierTxt = supplierTxt;
    }
}
