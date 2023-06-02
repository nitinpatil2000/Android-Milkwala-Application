package com.technosoul.milkwala.receiveProduct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SupplierOrder {
    private int supplierId;
    private int productId;
    private int orderedQuantity;
    private Date supplierOrderDate;

    public SupplierOrder(int supplierId, int productId, int orderedQuantity, Date supplierOrderDate) {
        this.supplierId = supplierId;
        this.productId = productId;
        this.orderedQuantity = orderedQuantity;
        this.supplierOrderDate = supplierOrderDate;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public Date getSupplierOrderDate() {
        return supplierOrderDate;
    }

    public void setSupplierOrderDate(Date supplierOrderDate) {
        this.supplierOrderDate = supplierOrderDate;
    }

    public SupplierOrder() {
    }

    @Override
    public String toString() {
        return "SupplierOrder{" +
                "supplierId=" + supplierId +
                ", productId=" + productId +
                ", orderedQuantity=" + orderedQuantity +
                ", supplierOrderDate=" + supplierOrderDate +
                '}';
    }
}


