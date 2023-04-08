package com.technosoul.milkwala.delivery;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "deliveryBoys")
public class DeliverDetails {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "deliver_id")
    private int deliverId;

    @ColumnInfo(name = "deliver_name")
    private String deliveryName;

    @ColumnInfo(name = "deliver_address")
    private String deliveryAddress;

    @ColumnInfo(name = "deliver_contact_no")
    private String deliveryContactNo;

    @ColumnInfo(name = "delivery_alter_no")
    private String deliveryAlterNo;

    public DeliverDetails(int deliverId, String deliveryName, String deliveryAddress, String deliveryContactNo, String deliveryAlterNo) {
        this.deliverId = deliverId;
        this.deliveryName = deliveryName;
        this.deliveryAddress = deliveryAddress;
        this.deliveryContactNo = deliveryContactNo;
        this.deliveryAlterNo = deliveryAlterNo;
    }

    @Ignore
    public DeliverDetails(String deliveryName, String deliveryAddress, String deliveryContactNo, String deliveryAlterNo) {
        this.deliveryName = deliveryName;
        this.deliveryAddress = deliveryAddress;
        this.deliveryContactNo = deliveryContactNo;
        this.deliveryAlterNo = deliveryAlterNo;
    }

    public DeliverDetails(){

    }

    public int getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(int deliverId) {
        this.deliverId = deliverId;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryContactNo() {
        return deliveryContactNo;
    }

    public void setDeliveryContactNo(String deliveryContactNo) {
        this.deliveryContactNo = deliveryContactNo;
    }

    public String getDeliveryAlterNo() {
        return deliveryAlterNo;
    }

    public void setDeliveryAlterNo(String deliveryAlterNo) {
        this.deliveryAlterNo = deliveryAlterNo;
    }
}
