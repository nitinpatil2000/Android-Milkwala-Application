package com.technosoul.milkwala.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.technosoul.milkwala.db.DeliveryPerson;

import java.util.List;

@Dao
public interface DeliveryPersonDao {

    @Query("SELECT * FROM delivery_boys")
    List<DeliveryPerson> getAllDeliveryBoys();

    @Insert
    void addDeliver(DeliveryPerson deliveryPerson);

    @Update
    void updateDeliver(DeliveryPerson deliveryPerson);

    @Delete
    void deleteDeliver(DeliveryPerson deliveryPerson);

    @Query("SELECT * FROM delivery_boys WHERE delivery_boy_id = :delivery_person_id")
    DeliveryPerson getDeliveryPersonById(int delivery_person_id);

    @Query("DELETE FROM delivery_boys WHERE delivery_boy_id = :delivery_boy_id")
    void deleteDeliveryBoyId(int delivery_boy_id);

}
