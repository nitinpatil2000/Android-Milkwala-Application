package com.technosoul.milkwala.Auth;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "login")
public class Login {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "login_id")
    private int loginId;

    @ColumnInfo(name = "email_id")
    private String emailId;

    @ColumnInfo(name = "password")
    private String password;


    public Login(int loginId, String emailId, String password) {
        this.loginId = loginId;
        this.emailId = emailId;
        this.password = password;
    }

    public Login(){

    }


    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
