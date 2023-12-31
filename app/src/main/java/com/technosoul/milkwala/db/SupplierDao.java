package com.technosoul.milkwala.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface SupplierDao {

    @Query("select * from supplier")
    List<Supplier> getAllSuppliers();

    @Insert
    void addSupplier(ArrayList<Supplier> supplier);

    @Query("SELECT * FROM supplier WHERE supplier_id = :supplierId")
    Supplier getSupplierById(int supplierId);

    @Update
    void updateSupplier(Supplier supplier);

    @Delete
    void deleteSupplier(Supplier supplier);

    @Query("DELETE FROM supplier WHERE supplier_id = :supplierId")
    void deleteById(int supplierId);



}
