package com.technosoul.milkwala.delivery;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.technosoul.milkwala.products.ProductDetails;

import java.util.List;

@Dao
public interface DeliveryDetailDao {

    @Query("select * from deliveryBoys")
    List<DeliverDetails> getAllDetails();

    @Insert
    void addDeliver(DeliverDetails deliverDetails);

    @Update
    void updateDeliver(DeliverDetails deliverDetails);

    @Delete
    void deleteDeliver(DeliverDetails deliverDetails);

    @Query("DELETE FROM deliveryBoys WHERE deliver_id = :deliver_details_id")
    void getDeliverById(int deliver_details_id);

}
