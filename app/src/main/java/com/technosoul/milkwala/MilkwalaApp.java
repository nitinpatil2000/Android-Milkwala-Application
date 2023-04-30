package com.technosoul.milkwala;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.technosoul.milkwala.managers.SharedPreferenceManager;
import com.technosoul.milkwala.utils.Constants;

public class MilkwalaApp extends Application {

    private static SharedPreferences sSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        sSharedPreferences = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferenceManager.getInstance().setSharedPreference(sSharedPreferences);
    }

    public static SharedPreferences getSharedPreference() {
        return sSharedPreferences;
    }
}
