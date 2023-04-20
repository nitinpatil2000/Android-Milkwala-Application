package com.technosoul.milkwala.products;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.technosoul.milkwala.Supplier.Supplier;

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

    public ProductDetails(int productDetailsId,int supplierId, String productDetailsName, String productSupplierRate, String productVenderRate, String productDetailsUnit, String productDetailsMrp) {
        this.productDetailsId = productDetailsId;
        this.supplierId = supplierId;
        this.productDetailsName = productDetailsName;
        this.productSupplierRate = productSupplierRate;
        this.productVenderRate = productVenderRate;
        this.productDetailsUnit = productDetailsUnit;
        this.productDetailsMrp = productDetailsMrp;
    }

    @Ignore
    public ProductDetails(String productDetailsName, String productDetailsUnit, String productDetailsMrp, String productSupplierRate, String productVenderRate) {
        this.productDetailsName = productDetailsName;
        this.productDetailsUnit = productDetailsUnit;
        this.productSupplierRate = productSupplierRate;
        this.productVenderRate = productVenderRate;
        this.productDetailsMrp = productDetailsMrp;

    }

    @Ignore
    public ProductDetails(String productDetailsName, String productDetailsUnit, String productDetailsMrp) {
        this.productDetailsName = productDetailsName;
        this.productDetailsUnit = productDetailsUnit;
        this.productDetailsMrp = productDetailsMrp;
    }

    public ProductDetails() {
    }

    public int getProductDetailsId() {
        return productDetailsId;
    }

    public void setProductDetailsId(int productDetailsId) {
        this.productDetailsId = productDetailsId;
    }



    public String getProductDetailsName() {
        return productDetailsName;
    }

    public void setProductDetailsName(String productDetailsName) {
        this.productDetailsName = productDetailsName;
    }

    public int getSupplierId() {
        return supplierId;
    }
//
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getProductDetailsUnit() {
        return productDetailsUnit;
    }

    public void setProductDetailsUnit(String productDetailsUnit) {
        this.productDetailsUnit = productDetailsUnit;
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

    public String getProductDetailsMrp() {
        return productDetailsMrp;
    }

    public void setProductDetailsMrp(String productDetailsMrp) {
        this.productDetailsMrp = productDetailsMrp;
    }


}
