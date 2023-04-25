package com.technosoul.milkwala.receiveProduct;

import androidx.room.Dao;
import androidx.room.Insert;

import com.technosoul.milkwala.products.ProductDetails;

@Dao
public interface DailyReceiveDao {
    @Insert
    void addDailyReceiveProduct(DailyReceiveProduct dailyReceiveProduct);

}
