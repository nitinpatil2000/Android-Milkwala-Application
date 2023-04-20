package com.technosoul.milkwala.ReceivedProduct;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "receive_product")
public class ReceivedProduct {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "receive_p_id")
    private int ReceivedProductId;

    @ColumnInfo(name = "receive_p_name")
    private String ReceivedProductName;

    @ColumnInfo(name = "receive_p_unit")
    private String ReceivedProductUnit;

    @ColumnInfo(name = "receive_p_mrp")
    private String ReceivedProductMrp;

    @ColumnInfo(name = "receive_p_amount")
    private Long TotalAmount;

    public ReceivedProduct(int receivedProductId, String receivedProductName, String receivedProductUnit, String receivedProductMrp, Long totalAmount) {
        ReceivedProductId = receivedProductId;
        ReceivedProductName = receivedProductName;
        ReceivedProductUnit = receivedProductUnit;
        ReceivedProductMrp = receivedProductMrp;
        TotalAmount = totalAmount;
    }

    public ReceivedProduct() {
    }

    @Ignore
    public ReceivedProduct(String receivedProductName, String receivedProductUnit, String receivedProductMrp, Long totalAmount) {
        ReceivedProductName = receivedProductName;
        ReceivedProductUnit = receivedProductUnit;
        ReceivedProductMrp = receivedProductMrp;
        TotalAmount = totalAmount;
    }

    public int getReceivedProductId() {
        return ReceivedProductId;
    }

    public void setReceivedProductId(int receivedProductId) {
        ReceivedProductId = receivedProductId;
    }

    public String getReceivedProductName() {
        return ReceivedProductName;
    }

    public void setReceivedProductName(String receivedProductName) {
        ReceivedProductName = receivedProductName;
    }

    public String getReceivedProductUnit() {
        return ReceivedProductUnit;
    }

    public void setReceivedProductUnit(String receivedProductUnit) {
        ReceivedProductUnit = receivedProductUnit;
    }

    public String getReceivedProductMrp() {
        return ReceivedProductMrp;
    }

    public void setReceivedProductMrp(String receivedProductMrp) {
        ReceivedProductMrp = receivedProductMrp;
    }

    public Long getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        TotalAmount = totalAmount;
    }
}
