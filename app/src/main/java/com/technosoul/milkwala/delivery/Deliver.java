package com.technosoul.milkwala.delivery;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "delivery_boys")
public class Deliver {

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
   private  String deliveryBoyNumber;

    @ColumnInfo(name = "delivery_boy_alter_no")
    private String deliveryBoyAlterNo;

    public Deliver(int deliverBoyId, String deliveryBoyName, String deliveryBoyAddress, String deliveryBoyCity, String deliveryBoyNumber, String deliveryBoyAlterNo) {
        this.deliverBoyId = deliverBoyId;
        this.deliveryBoyName = deliveryBoyName;
        this.deliveryBoyAddress = deliveryBoyAddress;
        this.deliveryBoyCity = deliveryBoyCity;
        this.deliveryBoyNumber = deliveryBoyNumber;
        this.deliveryBoyAlterNo = deliveryBoyAlterNo;
    }

    @Ignore
    public Deliver(String deliveryBoyName, String deliveryBoyAddress, String deliveryBoyCity, String deliveryBoyNumber, String deliveryBoyAlterNo) {
        this.deliveryBoyName = deliveryBoyName;
        this.deliveryBoyAddress = deliveryBoyAddress;
        this.deliveryBoyCity = deliveryBoyCity;
        this.deliveryBoyNumber = deliveryBoyNumber;
        this.deliveryBoyAlterNo = deliveryBoyAlterNo;
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

    public String getDeliveryBoyNumber() {
        return deliveryBoyNumber;
    }

    public void setDeliveryBoyNumber(String deliveryBoyNumber) {
        this.deliveryBoyNumber = deliveryBoyNumber;
    }

    public String getDeliveryBoyAlterNo() {
        return deliveryBoyAlterNo;
    }

    public void setDeliveryBoyAlterNo(String deliveryBoyAlterNo) {
        this.deliveryBoyAlterNo = deliveryBoyAlterNo;
    }
}
