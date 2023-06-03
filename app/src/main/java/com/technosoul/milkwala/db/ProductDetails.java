package com.technosoul.milkwala.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.technosoul.milkwala.db.Supplier;

import kotlin.jvm.Transient;

@Entity(tableName = "productDetails", foreignKeys = {
        @ForeignKey(entity = Supplier.class,
                parentColumns = "supplier_id",
                childColumns = "supplier_id",
                onDelete = ForeignKey.CASCADE)
})
public class ProductDetails {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_details_id")
    private int productDetailsId;

    @ColumnInfo(name = "supplier_id")
    private int supplierId;

    @ColumnInfo(name = "product_details_name")
    private String productDetailsName;

    @ColumnInfo(name = "product_supplier_rate")
    private String productSupplierRate;

    @ColumnInfo(name = "product_vender_rate")
    private String productVenderRate;

    @ColumnInfo(name = "product_details_unit")
    private String productDetailsUnit;

    @ColumnInfo(name = "product_details_mrp")
    private String productDetailsMrp;

    @Transient
    private Long productDetailsQuantity;


    public ProductDetails(){

    }

    public ProductDetails(String productDetailsName, String productSupplierRate, String productVenderRate, String productDetailsUnit, String productDetailsMrp) {
        this.productDetailsName = productDetailsName;
        this.productSupplierRate = productSupplierRate;
        this.productVenderRate = productVenderRate;
        this.productDetailsUnit = productDetailsUnit;
        this.productDetailsMrp = productDetailsMrp;
    }

    @Ignore
    public ProductDetails(String productDetailsName, String productDetailsUnit, String productDetailsMrp) {
        this.productDetailsName = productDetailsName;
        this.productDetailsUnit = productDetailsUnit;
        this.productDetailsMrp = productDetailsMrp;
    }

    @Ignore
    public ProductDetails(String productDetailsName, Long productDetailsQuantity) {
        this.productDetailsName = productDetailsName;
        this.productDetailsQuantity = productDetailsQuantity;
        this.productDetailsId = productDetailsId;
    }

    public int getProductDetailsId() {
        return productDetailsId;
    }

    public void setProductDetailsId(int productDetailsId) {
        this.productDetailsId = productDetailsId;
    }

    public Long getProductDetailsQuantity() {
        return productDetailsQuantity;
    }
//
    public void setProductDetailsQuantity(Long productDetailsQuantity) {
        this.productDetailsQuantity = productDetailsQuantity;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getProductDetailsName() {
        return productDetailsName;
    }

    public void setProductDetailsName(String productDetailsName) {
        this.productDetailsName = productDetailsName;
    }

    public String getProductSupplierRate() {
        return productSupplierRate;
    }

    public void setProductSupplierRate(String productSupplierRate) {
        this.productSupplierRate = productSupplierRate;
    }

    public String getProductVenderRate() {
        return productVenderRate;
    }

    public void setProductVenderRate(String productVenderRate) {
        this.productVenderRate = productVenderRate;
    }

    public String getProductDetailsUnit() {
        return productDetailsUnit;
    }

    public void setProductDetailsUnit(String productDetailsUnit) {
        this.productDetailsUnit = productDetailsUnit;
    }

    public String getProductDetailsMrp() {
        return productDetailsMrp;
    }

    public void setProductDetailsMrp(String productDetailsMrp) {
        this.productDetailsMrp = productDetailsMrp;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "productDetailsId=" + productDetailsId +
                ", supplierId=" + supplierId +
                ", productDetailsName='" + productDetailsName + '\'' +
                ", productSupplierRate='" + productSupplierRate + '\'' +
                ", productVenderRate='" + productVenderRate + '\'' +
                ", productDetailsUnit='" + productDetailsUnit + '\'' +
                ", productDetailsMrp='" + productDetailsMrp + '\'' +
                ", productDetailsQuantity=" + productDetailsQuantity +
                '}';
    }
}