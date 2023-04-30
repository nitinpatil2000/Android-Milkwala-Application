package com.technosoul.milkwala.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import javax.xml.transform.sax.SAXResult;

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

    @ColumnInfo(name = "customer_number")
    private String customerNumber;

    @ColumnInfo(name = "customer_alter_no")
    private String customerAlterNo;

    public Customer(int customerId, String customerName, String customerAddress, String customerNumber, String customerCity) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerNumber = customerNumber;
    }

    @Ignore
    public Customer(String customerName, String customerAddress, String customerCity, String customerNumber, String customerAlterNo) {
        this.customerName = customerName;
        this.customerCity = customerCity;
        this.customerAddress = customerAddress;
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

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerAlterNo() {
        return customerAlterNo;
    }

    public void setCustomerAlterNo(String customerAlterNo) {
        this.customerAlterNo = customerAlterNo;
    }
}
