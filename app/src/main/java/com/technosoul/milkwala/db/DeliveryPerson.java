package com.technosoul.milkwala.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "delivery_boys")
public class DeliveryPerson {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "delivery_boy_id")
    private int deliverBoyId;

    @ColumnInfo(name = "delivery_boy_name")
    private String deliveryBoyName;

    @ColumnInfo(name = "delivery_boy_add")
    private String deliveryBoyAddress;

    @ColumnInfo(name = "delivery_boy_city")
    private String deliveryBoyCity;

    @ColumnInfo(name = "delivery_boy_number")
   private  Long deliveryBoyNumber;

    @ColumnInfo(name = "delivery_boy_alter_no")
    private Long deliveryBoyAlterNo;

    @ColumnInfo(name = "delivery_login_email")
    private String deliveryBoyEmail;

    @ColumnInfo(name = "delivery_login_password")
    private String deliveryBoyPassword;



    public DeliveryPerson(String deliveryBoyName, String deliveryBoyAddress, String deliveryBoyCity
            , Long deliveryBoyNumber, Long deliveryBoyAlterNo, String deliveryBoyEmail, String deliveryBoyPassword) {
        this.deliveryBoyName = deliveryBoyName;
        this.deliveryBoyAddress = deliveryBoyAddress;
        this.deliveryBoyCity = deliveryBoyCity;
        this.deliveryBoyNumber = deliveryBoyNumber;
        this.deliveryBoyAlterNo = deliveryBoyAlterNo;
        this.deliveryBoyEmail = deliveryBoyEmail;
        this.deliveryBoyPassword = deliveryBoyPassword;
    }

    public int getDeliverBoyId() {
        return deliverBoyId;
    }

    public void setDeliverBoyId(int deliverBoyId) {
        this.deliverBoyId = deliverBoyId;
    }

    public String getDeliveryBoyName() {
        return deliveryBoyName;
    }

    public void setDeliveryBoyName(String deliveryBoyName) {
        this.deliveryBoyName = deliveryBoyName;
    }

    public String getDeliveryBoyAddress() {
        return deliveryBoyAddress;
    }

    public void setDeliveryBoyAddress(String deliveryBoyAddress) {
        this.deliveryBoyAddress = deliveryBoyAddress;
    }

    public String getDeliveryBoyCity() {
        return deliveryBoyCity;
    }

    public void setDeliveryBoyCity(String deliveryBoyCity) {
        this.deliveryBoyCity = deliveryBoyCity;
    }

    public Long getDeliveryBoyNumber() {
        return deliveryBoyNumber;
    }

    public void setDeliveryBoyNumber(Long deliveryBoyNumber) {
        this.deliveryBoyNumber = deliveryBoyNumber;
    }

    public Long getDeliveryBoyAlterNo() {
        return deliveryBoyAlterNo;
    }

    public void setDeliveryBoyAlterNo(Long deliveryBoyAlterNo) {
        this.deliveryBoyAlterNo = deliveryBoyAlterNo;
    }

    public String getDeliveryBoyEmail() {
        return deliveryBoyEmail;
    }

    public void setDeliveryBoyEmail(String deliveryBoyEmail) {
        this.deliveryBoyEmail = deliveryBoyEmail;
    }

    public String getDeliveryBoyPassword() {
        return deliveryBoyPassword;
    }

    public void setDeliveryBoyPassword(String deliveryBoyPassword) {
        this.deliveryBoyPassword = deliveryBoyPassword;
    }
}
