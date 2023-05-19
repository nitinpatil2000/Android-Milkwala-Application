package com.technosoul.milkwala.ui.masterinfo.products;

public class ProductFromServer {
    private int productId;
    private String productName;
    private double productSupplierRate;
    private double productVendorRate;
    private String productUnit;
    private double productMrp;
    private int supplierId;


    public ProductFromServer(int supplierId, String productName, double productSupplierRate, double productVendorRate, String productUnit, double productMrp) {
        this.supplierId = supplierId;
        this.productName = productName;
        this.productSupplierRate = productSupplierRate;
        this.productVendorRate = productVendorRate;
        this.productUnit = productUnit;
        this.productMrp = productMrp;
    }

    public ProductFromServer(){

    }

    @Override
    public String toString() {
        return "ProductFromServer{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productSupplierRate=" + productSupplierRate +
                ", productVendorRate=" + productVendorRate +
                ", productUnit='" + productUnit + '\'' +
                ", productMrp=" + productMrp +
                ", supplierId=" + supplierId +
                '}';
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductSupplierRate() {
        return productSupplierRate;
    }

    public void setProductSupplierRate(double productSupplierRate) {
        this.productSupplierRate = productSupplierRate;
    }

    public double getProductVendorRate() {
        return productVendorRate;
    }

    public void setProductVendorRate(double productVendorRate) {
        this.productVendorRate = productVendorRate;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public double getProductMrp() {
        return productMrp;
    }

    public void setProductMrp(double productMrp) {
        this.productMrp = productMrp;
    }

}