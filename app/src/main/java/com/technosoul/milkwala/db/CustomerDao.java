package com.technosoul.milkwala.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CustomerDao {

    @Query("SELECT * FROM customer WHERE customer_id = :customerId")
    Customer getCustomerById(int customerId);

    @Query("select * from customer")
    List<Customer> getAllCustomers();

    @Insert
    void addCustomer(Customer customer);

    @Update
    void updateCustomer(Customer customer);

    @Delete
    void deleteCustomer(Customer customer);

    @Query("DELETE FROM customer WHERE customer_id = :customer_id")
    void deleteCustomerById(int customer_id);


}

