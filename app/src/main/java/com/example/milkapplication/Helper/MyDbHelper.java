package com.example.milkapplication.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.milkapplication.Supplier.Supplier;

import java.util.ArrayList;

public class MyDbHelper extends SQLiteOpenHelper {
//    private static final String DATABASE_NAME = "MilkDb";
//    private static final int DATABASE_VERSION = 1;
//    private static final String TABLE_SUPPLIERS = "suppliers";
//
//    private static final String  SUPPLIER_ID = "id";
//    private static final String SUPPLIER_NAME = "supplier_text";
//    private static final String SUPPLIER_SUB_TEXT = "supplier_sub_text";

    public MyDbHelper(@Nullable Context context) {
        super(context, "MilkDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table  suppliers (supplierId INTEGER PRIMARY KEY AUTOINCREMENT, supplierText TEXT)");
//                "(" +
//                 "id" + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "supplier_text" + " TEXT," +
//                "supplier_sub_text"+ " TEXT"
//                + ")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists suppliers");
        onCreate(db);
    }

    public void insertData(String supplierText){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("supplierText", supplierText);
        db.insert("suppliers", null,contentValues);
        db.close();
    }

    public ArrayList<Supplier> getData() {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
//        String[] columns = {"name", "number"};
        Cursor cursor = db.rawQuery("SELECT * FROM suppliers", null);
        if (cursor != null && cursor.getCount() > 0) {
            int nameIndex = cursor.getColumnIndex("supplierText");
            while (cursor.moveToNext()) {
                String name = cursor.getString(nameIndex);
                suppliers.add(new Supplier(name));
            }
            cursor.close();
            db.close();
        }
        return suppliers;
    }




}
