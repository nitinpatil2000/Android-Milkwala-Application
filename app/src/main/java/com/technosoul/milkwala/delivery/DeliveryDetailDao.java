package com.technosoul.milkwala.delivery;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface DeliveryDetailDao {

    @Query("select * from delivery_boys")
    List<Deliver> getAllDeliveryBoys();

    @Insert
    void addDeliver(Deliver deliver);

    @Update
    void updateDeliver(Deliver deliver);

    @Delete
    void deleteDeliver(Deliver deliver);

    @Query("DELETE FROM delivery_boys WHERE delivery_boy_id = :delivery_boy_id")
    void getDeliverById(int delivery_boy_id);

}
