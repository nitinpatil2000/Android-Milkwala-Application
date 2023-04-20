package com.technosoul.milkwala.ReceivedProduct;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.technosoul.milkwala.products.ProductDetails;

import java.util.List;

@Dao
public interface ReceiveProductDao {

    @Query("select * from receive_product")
    List<ReceivedProduct> getAllReceiveProduct();

    @Insert
    void addReceiveProduct(ReceivedProduct receivedProduct);

    @Update
    void updateReceiveProduct(ReceivedProduct receivedProduct);

    @Delete
    void deleteReceiveProduct(ReceivedProduct receivedProduct);

    @Query("DELETE FROM receive_product WHERE receive_p_id = :receive_p_id")
    void getReceiveProductById(int receive_p_id);
}
