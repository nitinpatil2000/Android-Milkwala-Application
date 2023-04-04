package com.example.milkapplication.products;

public class ProductDetails {
    String productDetailsText;
    String productDetailsUnit;
    String productDetailsMrp;

    public ProductDetails(String productDetailsText, String productDetailsUnit, String productDetailsMrp) {
        this.productDetailsText = productDetailsText;
        this.productDetailsUnit = productDetailsUnit;
        this.productDetailsMrp = productDetailsMrp;
    }

    public ProductDetails() {
    }


    public String getProductDetailsText() {
        return productDetailsText;
    }

    public void setProductDetailsText(String productDetailsText) {
        this.productDetailsText = productDetailsText;
    }

    public String getProductDetailsUnit() {
        return productDetailsUnit;
    }

    public void setProductDetailsUnit(String productDetailsUnit) {
        this.productDetailsUnit = productDetailsUnit;
    }

    public String getProductDetailsMrp() {
        return productDetailsMrp;
    }

    public void setProductDetailsMrp(String productDetailsMrp) {
        this.productDetailsMrp = productDetailsMrp;
    }
}
