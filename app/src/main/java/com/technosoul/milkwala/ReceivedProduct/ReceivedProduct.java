package com.technosoul.milkwala.ReceivedProduct;

public class ReceivedProduct {

    private int ReceivedProductId;

    private String ReceivedProductName;

    private String ReceivedProductUnit;

    private String ReceivedProductMrp;

    private int Quantity;

    private int TotalAmount;

    public ReceivedProduct(int receivedProductId, String receivedProductName, String receivedProductUnit, String receivedProductMrp, int quantity, int totalAmount) {
        ReceivedProductId = receivedProductId;
        ReceivedProductName = receivedProductName;
        ReceivedProductUnit = receivedProductUnit;
        ReceivedProductMrp = receivedProductMrp;
        Quantity = quantity;
        TotalAmount = totalAmount;
    }


    public ReceivedProduct(int receivedProductId, String receivedProductName, String receivedProductUnit, String receivedProductMrp, int totalAmount) {
        ReceivedProductId = receivedProductId;
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

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        TotalAmount = totalAmount;
    }
}
