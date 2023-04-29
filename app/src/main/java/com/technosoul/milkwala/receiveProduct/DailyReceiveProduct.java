package com.technosoul.milkwala.receiveProduct;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.technosoul.milkwala.products.ProductDetails;

import java.util.Date;

@Entity(tableName = "daily_receive_product", foreignKeys = {
        @ForeignKey(entity = ProductDetails.class,
                parentColumns = "product_details_id",
                childColumns = "product_details_id",
                onDelete = ForeignKey.CASCADE)
})
@TypeConverters(DataConverter.class)
public class DailyReceiveProduct {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "daily_receive_id")
    private int dailyReceiveId;

    @ColumnInfo(name = "product_details_id")
    private int productDetailsId;

    @ColumnInfo(name = "received_product_date")
    private Date receivedProductDate;

    @ColumnInfo(name = "received_product_quantity")
    private int receivedProductQuantity;


    // TODO add productDetailsMrp

    public DailyReceiveProduct(int dailyReceiveId, int productDetailsId,  Date receivedProductDate,int receivedProductQuantity) {
        this.dailyReceiveId = dailyReceiveId;
        this.productDetailsId = productDetailsId;
        this.receivedProductDate = receivedProductDate;
        this.receivedProductQuantity = receivedProductQuantity;
    }

    @Ignore
    public DailyReceiveProduct(int productDetailsId, Date receivedProductDate, int receivedProductQuantity) {
        this.productDetailsId = productDetailsId;
        this.receivedProductDate = receivedProductDate;
        this.receivedProductQuantity = receivedProductQuantity;
    }

    public DailyReceiveProduct(){

    }

    public int getDailyReceiveId() {
        return dailyReceiveId;
    }

    public void setDailyReceiveId(int dailyReceiveId) {
        this.dailyReceiveId = dailyReceiveId;
    }

    public int getProductDetailsId() {
        return productDetailsId;
    }

    public void setProductDetailsId(int productDetailsId) {
        this.productDetailsId = productDetailsId;
    }

    public Date getReceivedProductDate() {
        return receivedProductDate;
    }
//
    public void setReceivedProductDate(Date receivedProductDate) {
        this.receivedProductDate = receivedProductDate;
    }

    public int getReceivedProductQuantity() {
        return receivedProductQuantity;
    }

    public void setReceivedProductQuantity(int receivedProductQuantity) {
        this.receivedProductQuantity = receivedProductQuantity;
    }

    @Override
    public String toString() {
        return "DailyReceiveProduct{" +
                "dailyReceiveId=" + dailyReceiveId +
                ", productDetailsId=" + productDetailsId +
                ", receivedProductDate=" + receivedProductDate +
                ", receivedProductQuantity=" + receivedProductQuantity +
                '}';
    }
}
