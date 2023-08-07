package com.technosoul.milkwala.ui.masterinfo.customer;

import java.util.List;

public class ProductForCustomerServer {
    private int productForCustomerId;
    private int customerId;
    private List<Integer> productIds;

    public ProductForCustomerServer(){
    }


    public ProductForCustomerServer(int customerId, List<Integer> productIds) {
        this.customerId = customerId;
        this.productIds = productIds;
    }

    public int getProductForCustomerId() {
        return productForCustomerId;
    }

    public void setProductForCustomerId(int productForCustomerId) {
        this.productForCustomerId = productForCustomerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }
}
