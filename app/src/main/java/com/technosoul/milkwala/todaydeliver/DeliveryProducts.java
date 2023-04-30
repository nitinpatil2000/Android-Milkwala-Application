package com.technosoul.milkwala.todaydeliver;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.technosoul.milkwala.db.Customer;

@Entity(tableName = "delivery_products", foreignKeys = {
        @ForeignKey(entity = Customer.class,
                parentColumns = "customer_id",
                childColumns = "customer_id",
                onDelete = ForeignKey.CASCADE)
})
public class DeliveryProducts {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "delivery_products_id")
    private int deliveryProductsId;

    @ColumnInfo(name = "customer_id")
    private int customerId;

    @ColumnInfo(name = "customer_product_name")
    private String customerProductName;

    @ColumnInfo(name = "customer_product_unit")
    private String customerProductUnit;

    @ColumnInfo(name = "customer_product_qty")
    private int customerProductQty;

    @ColumnInfo(name = "customer_product_rate")
    private int customerProductRate;

    @ColumnInfo(name = "customer_product_given_qty")
    private int receivedProductQuantity;


    // TODO add boolean checked - isDelivered


    public DeliveryProducts(int deliveryProductsId, int customerId, String customerProductName, String customerProductUnit, int customerProductQty, int customerProductRate, int receivedProductQuantity) {
        this.deliveryProductsId = deliveryProductsId;
        this.customerId = customerId;
        this.customerProductName = customerProductName;
        this.customerProductUnit = customerProductUnit;
        this.customerProductQty = customerProductQty;
        this.customerProductRate = customerProductRate;
        this.receivedProductQuantity = receivedProductQuantity;
    }

    public int getDeliveryProductsId() {
        return deliveryProductsId;
    }

    public void setDeliveryProductsId(int deliveryProductsId) {
        this.deliveryProductsId = deliveryProductsId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerProductName() {
        return customerProductName;
    }

    public void setCustomerProductName(String customerProductName) {
        this.customerProductName = customerProductName;
    }

    public String getCustomerProductUnit() {
        return customerProductUnit;
    }

    public void setCustomerProductUnit(String customerProductUnit) {
        this.customerProductUnit = customerProductUnit;
    }

    public int getCustomerProductQty() {
        return customerProductQty;
    }

    public void setCustomerProductQty(int customerProductQty) {
        this.customerProductQty = customerProductQty;
    }

    public int getCustomerProductRate() {
        return customerProductRate;
    }

    public void setCustomerProductRate(int customerProductRate) {
        this.customerProductRate = customerProductRate;
    }

    public int getReceivedProductQuantity() {
        return receivedProductQuantity;
    }

    public void setReceivedProductQuantity(int receivedProductQuantity) {
        this.receivedProductQuantity = receivedProductQuantity;
    }

    @Override
    public String toString() {
        return "DeliveryProducts{" +
                "deliveryProductsId=" + deliveryProductsId +
                ", customerId=" + customerId +
                ", customerProductName='" + customerProductName + '\'' +
                ", customerProductUnit='" + customerProductUnit + '\'' +
                ", customerProductQty=" + customerProductQty +
                ", customerProductRate=" + customerProductRate +
                ", receivedProductQuantity=" + receivedProductQuantity +
                '}';
    }
}
