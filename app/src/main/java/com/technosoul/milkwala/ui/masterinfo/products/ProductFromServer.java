package com.technosoul.milkwala.ui.masterinfo.products;

public class ProductFromServer {
    private int productId;
    private String productName;
    private String productType;
    private String productUnit;
    private float productSupplierRate;
    private float productWholesaleRate;
    private float productMrpRetailerRate;
    private int supplierId;


    public ProductFromServer(String productName, String productType, String productUnit, float productSupplierRate, float productWholesaleRate, float productMrpRetailerRate, int supplierId) {
        this.productName = productName;
        this.productType = productType;
        this.productUnit = productUnit;
        this.productSupplierRate = productSupplierRate;
        this.productWholesaleRate = productWholesaleRate;
        this.productMrpRetailerRate = productMrpRetailerRate;
        this.supplierId = supplierId;
    }

    public ProductFromServer(){

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public float getProductSupplierRate() {
        return productSupplierRate;
    }

    public void setProductSupplierRate(float productSupplierRate) {
        this.productSupplierRate = productSupplierRate;
    }

    public float getProductWholesaleRate() {
        return productWholesaleRate;
    }

    public void setProductWholesaleRate(float productWholesaleRate) {
        this.productWholesaleRate = productWholesaleRate;
    }

    public float getProductMrpRetailerRate() {
        return productMrpRetailerRate;
    }

    public void setProductMrpRetailerRate(float productMrpRetailerRate) {
        this.productMrpRetailerRate = productMrpRetailerRate;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "ProductFromServer{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", productUnit='" + productUnit + '\'' +
                ", productSupplierRate=" + productSupplierRate +
                ", productWholesaleRate=" + productWholesaleRate +
                ", productMrpRetailerRate=" + productMrpRetailerRate +
                ", supplierId=" + supplierId +
                '}';
    }
}