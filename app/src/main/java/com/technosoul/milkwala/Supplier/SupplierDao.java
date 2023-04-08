package com.technosoul.milkwala.Supplier;

import android.widget.TextView;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SupplierDao {

    @Query("select * from supplier")
    List<Supplier> getAllSuppliers();

    @Insert
    void addSupplier(Supplier supplier);

    @Update
    void updateSupplier(Supplier supplier);

    @Delete
    void deleteSupplier(Supplier supplier);

    @Query("DELETE FROM supplier WHERE id = :supplierId")
    void deleteById(long supplierId);



}
