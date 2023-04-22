//package com.technosoul.milkwala.receiveProduct;
//
//import androidx.room.ColumnInfo;
//import androidx.room.Entity;
//import androidx.room.ForeignKey;
//import androidx.room.Ignore;
//import androidx.room.PrimaryKey;
//
//import com.technosoul.milkwala.products.ProductDetails;
//
//import java.sql.Date;
//
//
//@Entity(tableName = "receive_product")
////        , foreignKeys = {
////        @ForeignKey(entity = ProductDetails.class,
////                parentColumns = "product_details_id",
////                childColumns = "product_details_id",
////                onDelete = ForeignKey.CASCADE)
//
////})
//public class ReceivedProduct {
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "receive_p_id")
//    private int ReceivedProductId;
//
////    @ColumnInfo(name = "product_details_id")
////    private int productDetailsId;
//
////    @ColumnInfo(name = "receive_p_date")
////    private Date ReceiveProductDate;
//
//    @ColumnInfo(name = "receive_p_quantity")
//    private int ReceiveProductQuantity;
//
//    @ColumnInfo(name = "receive_p_amount")
//    private Long ReceiveProductTotalAmount;
//
//
//    public ReceivedProduct(int receivedProductId, int receiveProductQuantity, Long receiveProductTotalAmount) {
//        ReceivedProductId = receivedProductId;
////        this.productDetailsId = productDetailsId;
////        ReceiveProductDate = receiveProductDate;
//        ReceiveProductQuantity = receiveProductQuantity;
//        ReceiveProductTotalAmount = receiveProductTotalAmount;
//    }
//
//
//
//    @Ignore
//    public ReceivedProduct( int receiveProductQuantity, Long receiveProductTotalAmount) {
////        ReceiveProductDate = receiveProductDate;
//        ReceiveProductQuantity = receiveProductQuantity;
//        ReceiveProductTotalAmount = receiveProductTotalAmount;
//    }
//
//
//    public int getReceivedProductId() {
//        return ReceivedProductId;
//    }
//
//    public void setReceivedProductId(int receivedProductId) {
//        ReceivedProductId = receivedProductId;
//    }
//
////    public int getProductDetailsId() {
////        return productDetailsId;
////    }
////
////    public void setProductDetailsId(int productDetailsId) {
////        this.productDetailsId = productDetailsId;
////    }
//
////    public Date getReceiveProductDate() {
////        return ReceiveProductDate;
////    }
////
////    public void setReceiveProductDate(Date receiveProductDate) {
////        ReceiveProductDate = receiveProductDate;
////    }
//
//    public int getReceiveProductQuantity() {
//        return ReceiveProductQuantity;
//    }
//
//    public void setReceiveProductQuantity(int receiveProductQuantity) {
//        ReceiveProductQuantity = receiveProductQuantity;
//    }
//
//    public Long getReceiveProductTotalAmount() {
//        return ReceiveProductTotalAmount;
//    }
//
//    public void setReceiveProductTotalAmount(Long receiveProductTotalAmount) {
//        ReceiveProductTotalAmount = receiveProductTotalAmount;
//    }
//}