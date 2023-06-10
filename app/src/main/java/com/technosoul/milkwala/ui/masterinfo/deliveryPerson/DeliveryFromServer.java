package com.technosoul.milkwala.ui.masterinfo.deliveryPerson;

public class DeliveryFromServer {
    private int deliveryPersonId;
    private String deliveryPersonName;
    private String deliveryPersonEmail;
    private String deliveryPersonPassword;
    private String deliveryPersonAddress;
    private String deliveryPersonCity;
    private Long deliveryPersonContactNo;
    private Long deliveryPersonAlterNo;

    public DeliveryFromServer(String deliveryPersonName, String deliveryPersonEmail, String deliveryPersonPassword, String deliveryPersonAddress, String deliveryPersonCity, Long deliveryPersonContactNo, Long deliveryPersonAlterNo) {
        this.deliveryPersonName = deliveryPersonName;
        this.deliveryPersonEmail = deliveryPersonEmail;
        this.deliveryPersonPassword = deliveryPersonPassword;
        this.deliveryPersonAddress = deliveryPersonAddress;
        this.deliveryPersonCity = deliveryPersonCity;
        this.deliveryPersonContactNo = deliveryPersonContactNo;
        this.deliveryPersonAlterNo = deliveryPersonAlterNo;
    }

    public DeliveryFromServer(){

    }
    public int getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public void setDeliveryPersonId(int deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
    }

    public String getDeliveryPersonName() {
        return deliveryPersonName;
    }

    public void setDeliveryPersonName(String deliveryPersonName) {
        this.deliveryPersonName = deliveryPersonName;
    }

    public String getDeliveryPersonEmail() {
        return deliveryPersonEmail;
    }

    public void setDeliveryPersonEmail(String deliveryPersonEmail) {
        this.deliveryPersonEmail = deliveryPersonEmail;
    }

    public String getDeliveryPersonPassword() {
        return deliveryPersonPassword;
    }

    public void setDeliveryPersonPassword(String deliveryPersonPassword) {
        this.deliveryPersonPassword = deliveryPersonPassword;
    }

    public String getDeliveryPersonAddress() {
        return deliveryPersonAddress;
    }

    public void setDeliveryPersonAddress(String deliveryPersonAddress) {
        this.deliveryPersonAddress = deliveryPersonAddress;
    }

    public String getDeliveryPersonCity() {
        return deliveryPersonCity;
    }

    public void setDeliveryPersonCity(String deliveryPersonCity) {
        this.deliveryPersonCity = deliveryPersonCity;
    }

    public Long getDeliveryPersonContactNo() {
        return deliveryPersonContactNo;
    }

    public void setDeliveryPersonContactNo(Long deliveryPersonContactNo) {
        this.deliveryPersonContactNo = deliveryPersonContactNo;
    }

    public Long getDeliveryPersonAlterNo() {
        return deliveryPersonAlterNo;
    }

    public void setDeliveryPersonAlterNo(Long deliveryPersonAlterNo) {
        this.deliveryPersonAlterNo = deliveryPersonAlterNo;
    }

    @Override
    public String toString() {
        return "DeliveryFromServer{" +
                "deliveryPersonId=" + deliveryPersonId +
                ", deliveryPersonName='" + deliveryPersonName + '\'' +
                ", deliveryPersonEmail='" + deliveryPersonEmail + '\'' +
                ", deliveryPersonPassword='" + deliveryPersonPassword + '\'' +
                ", deliveryPersonAddress='" + deliveryPersonAddress + '\'' +
                ", deliveryPersonCity='" + deliveryPersonCity + '\'' +
                ", deliveryPersonContactNo=" + deliveryPersonContactNo +
                ", deliveryPersonAlterNo=" + deliveryPersonAlterNo +
                '}';
    }
}