package com.technosoul.milkwala.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customer")
public class Customer {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "customer_id")
    private int customerId;

    @ColumnInfo(name = "customer_name")
    private String customerName;

    @ColumnInfo(name = "customer_address")
    private String  customerAddress;

    @ColumnInfo(name = "customer_city")
    private String customerCity;

    @ColumnInfo(name = "customer_email")
    private String customerEmail;

    @ColumnInfo(name = "customer_type")
    private String customerType;

    @ColumnInfo(name = "customer_number")
    private Long customerNumber;

    @ColumnInfo(name = "customer_alter_no")
    private Long customerAlterNo;

    public Customer(String customerName, String customerAddress, String customerCity, String customerEmail, String customerType, Long customerNumber, Long customerAlterNo) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerEmail = customerEmail;
        this.customerType = customerType;
        this.customerNumber = customerNumber;
        this.customerAlterNo = customerAlterNo;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Long getCustomerAlterNo() {
        return customerAlterNo;
    }

    public void setCustomerAlterNo(Long customerAlterNo) {
        this.customerAlterNo = customerAlterNo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerCity='" + customerCity + '\'' +
                ", customerNumber=" + customerNumber +
                ", customerAlterNo=" + customerAlterNo +
                '}';
    }
}
