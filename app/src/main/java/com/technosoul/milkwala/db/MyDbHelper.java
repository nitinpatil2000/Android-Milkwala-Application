package com.technosoul.milkwala.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Supplier.class, ProductDetails.class, DeliveryPerson.class, Customer.class, Login.class, DailyReceiveProduct.class}, exportSchema = false, version =8)
public abstract class MyDbHelper extends RoomDatabase {
    private static  final  String DB_NAME = "milkDb";
    private static MyDbHelper instance;

    public static synchronized MyDbHelper getDB(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, MyDbHelper.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }
        checkLoginCredentials(instance);
        return instance;
    }

    private static void checkLoginCredentials(MyDbHelper myDbHelper) {
        LoginDao loginDao = myDbHelper.loginDao();
        Login login = loginDao.getLoginCredentials("nitin@gmail.com");
        if (login == null) {
            login = new Login();
            login.setEmailId("nitin@gmail.com");
            login.setPassword("admin123");
            loginDao.insert(login);
        }

        // TODO Check if another user exists
        login = loginDao.getLoginCredentials("db@gmail.com");
        if (login == null) {
            login = new Login();
            login.setEmailId("db@gmail.com ");
            login.setPassword("normal123");
            loginDao.insert(login);
        }
    }

    public abstract SupplierDao supplierDao();
    public abstract ProductDetailsDao productDetailsDto();
    public abstract DeliveryPersonDao deliveryDetailDao();
    public abstract CustomerDao customerDao();
    public abstract LoginDao loginDao ();
    public abstract DailyReceiveDao dailyReceiveDao();

}
