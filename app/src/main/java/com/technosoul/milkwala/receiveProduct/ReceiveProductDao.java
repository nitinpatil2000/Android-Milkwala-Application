//package com.technosoul.milkwala.receiveProduct;
//
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import java.util.List;
//
//@Dao
//public interface ReceiveProductDao {
//
//    @Query("select * from receive_product")
//    List<ReceiveProduct> getAllReceiveProduct();
//
//    @Insert
//    void addReceiveProduct(ReceiveProduct receivedProduct);
//
//    @Update
//    void updateReceiveProduct(ReceiveProduct receivedProduct);
//
//    @Delete
//    void deleteReceiveProduct(ReceiveProduct receivedProduct);
//
////    @Query("DELETE FROM receive_product WHERE receive_p_id = :receive_p_id")
////    void getReceiveProductById(int receive_p_id);
//}
