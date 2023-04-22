package com.technosoul.milkwala.receiveProduct;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "receive_product")
public class ReceiveProduct {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "receive_p_id")
    private int ReceivedProductId;

    @ColumnInfo(name = "receive_p_quantity")
    private int ReceiveProductQuantity;

    @ColumnInfo(name = "receive_p_amount")
    private Long ReceiveProductTotalAmount;

    public ReceiveProduct(int receivedProductId, int receiveProductQuantity, Long receiveProductTotalAmount) {
        ReceivedProductId = receivedProductId;
        ReceiveProductQuantity = receiveProductQuantity;
        ReceiveProductTotalAmount = receiveProductTotalAmount;
    }

    public ReceiveProduct(){
    }

    @Ignore
    public ReceiveProduct(int receiveProductQuantity, Long receiveProductTotalAmount) {
        ReceiveProductQuantity = receiveProductQuantity;
        ReceiveProductTotalAmount = receiveProductTotalAmount;
    }

    public int getReceivedProductId() {
        return ReceivedProductId;
    }

    public void setReceivedProductId(int receivedProductId) {
        ReceivedProductId = receivedProductId;
    }

    public int getReceiveProductQuantity() {
        return ReceiveProductQuantity;
    }

    public void setReceiveProductQuantity(int receiveProductQuantity) {
        ReceiveProductQuantity = receiveProductQuantity;
    }

    public Long getReceiveProductTotalAmount() {
        return ReceiveProductTotalAmount;
    }

    public void setReceiveProductTotalAmount(Long receiveProductTotalAmount) {
        ReceiveProductTotalAmount = receiveProductTotalAmount;
    }
}
