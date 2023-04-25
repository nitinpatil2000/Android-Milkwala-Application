package com.technosoul.milkwala.products;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface ProductDetailsDto {

    @Query("select * from productDetails")
    List<ProductDetails> getAllProducts();

    @Query("select *from productDetails where supplier_id = :supplier_id")
     List<ProductDetails> getProductBySupplierId(int supplier_id);

    @Query("select *from productDetails where product_details_id = :product_details_id")
    ProductDetails getProductById(int product_details_id);

    @Insert
    void addProduct(ProductDetails productDetails);

    @Update
    void updateProduct(ProductDetails productDetails);

    @Delete
    void deleteProduct(ProductDetails productDetails);

    @Query("DELETE FROM productDetails WHERE product_details_id = :product_details_id")
    void deleteProductById(int product_details_id);

    @Query("select *from productDetails where productDetailsQuantity = :product_details_quantity")
    ProductDetails getProductByQuantity(Long product_details_quantity);

}
