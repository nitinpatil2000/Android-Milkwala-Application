package com.technosoul.milkwala.customerorder;

import java.sql.Date;

public class CustomerOrder {
    private int customerOrderId;
    private int customerId;
    private int productId;
    private int deliveryPersonId;
    private int orderedQuantity;
    private Date customerOrderDate;

    public  CustomerOrder(){

    }

    public CustomerOrder(int customerOrderId, int customerId, int productId, int deliveryPersonId, int orderedQuantity, Date customerOrderDate) {
        this.customerOrderId = customerOrderId;
        this.customerId = customerId;
        this.productId = productId;
        this.deliveryPersonId = deliveryPersonId;
        this.orderedQuantity = orderedQuantity;
        this.customerOrderDate = customerOrderDate;
    }


    public int getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(int customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public void setDeliveryPersonId(int deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public Date getCustomerOrderDate() {
        return customerOrderDate;
    }

    public void setCustomerOrderDate(Date customerOrderDate) {
        this.customerOrderDate = customerOrderDate;
    }
}
