package com.technosoul.milkwala.products;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDetailsDto {

//    @Query("SELECT name, email FROM users")

    @Query("select * from productDetails")
//    @Query("select productDetailsName, productDetailsUnit, productDetailsMrp from productDetails")
    List<ProductDetails> getAllProducts();


    @Insert
    void addProduct(ProductDetails productDetails);

    @Update
    void updateProduct(ProductDetails productDetails);

    @Delete
    void deleteProduct(ProductDetails productDetails);

    @Query("DELETE FROM productDetails WHERE product_details_id = :product_details_id")
    void getProductById(int product_details_id);

}
