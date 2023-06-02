package com.technosoul.milkwala.db;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface DailyReceiveDao {
    @Insert
    void addDailyReceiveProduct(DailyReceiveProduct dailyReceiveProduct);

}
