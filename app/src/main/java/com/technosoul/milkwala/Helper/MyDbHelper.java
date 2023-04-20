package com.technosoul.milkwala.Helper;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.technosoul.milkwala.ReceivedProduct.ReceiveProductDao;
import com.technosoul.milkwala.ReceivedProduct.ReceivedProduct;
import com.technosoul.milkwala.Supplier.Supplier;
import com.technosoul.milkwala.Supplier.SupplierDao;
import com.technosoul.milkwala.customer.Customer;
import com.technosoul.milkwala.customer.CustomerDao;
import com.technosoul.milkwala.delivery.Deliver;
import com.technosoul.milkwala.delivery.DeliveryDetailDao;
import com.technosoul.milkwala.products.ProductDetails;
import com.technosoul.milkwala.products.ProductDetailsDto;

@Database(entities = {Supplier.class, ProductDetails.class, Deliver.class, Customer.class, ReceivedProduct.class}, exportSchema = false, version = 13)
public abstract class MyDbHelper extends RoomDatabase {
    private static  final  String DB_NAME = "supplierDb";
    private static MyDbHelper instance;

    public static synchronized MyDbHelper getDB(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, MyDbHelper.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract SupplierDao supplierDao();
    public abstract ProductDetailsDto productDetailsDto();
    public abstract DeliveryDetailDao deliveryDetailDao();
    public abstract CustomerDao customerDao();
    public abstract ReceiveProductDao receiveProductDao();

}
