package com.technosoul.milkwala.ui.masterinfo.customer;

public class CustomerFromServer {
    private int customerId;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private String customerType;
    private Long customerContactNo;
    private Long customerAlterNo;
    private int routeId;


    public CustomerFromServer(){

    }
    public CustomerFromServer(String customerName, String customerEmail, String customerAddress,String customerType,Long customerContactNo, Long customerAlterNo) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerType = customerType;
        this.customerContactNo = customerContactNo;
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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Long getCustomerContactNo() {
        return customerContactNo;
    }

    public void setCustomerContactNo(Long customerContactNo) {
        this.customerContactNo = customerContactNo;
    }

    public Long getCustomerAlterNo() {
        return customerAlterNo;
    }

    public void setCustomerAlterNo(Long customerAlterNo) {
        this.customerAlterNo = customerAlterNo;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    @Override
    public String toString() {
        return "CustomerFromServer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerContactNo=" + customerContactNo +
                ", customerAlterNo=" + customerAlterNo +
                ", customerType='" + customerType + '\'' +
                ", routeId=" + routeId +
                '}';
    }
}





