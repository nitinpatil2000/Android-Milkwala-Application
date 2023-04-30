package com.technosoul.milkwala.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface LoginDao {

    @Query("select * from login where email_id  = :emailId")
    Login getLoginCredentials(String emailId);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Login login);
}
