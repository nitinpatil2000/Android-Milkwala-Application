package com.technosoul.milkwala.utils;

import com.technosoul.milkwala.BuildConfig;

public class Constants {

    private static final String PKG = BuildConfig.APPLICATION_ID;
    public static final String SHARED_PREF_NAME = "MilkwalaSharedPreference";
    public static final String KEY_LOGIN_TYPE = PKG + ".login_type";
    public static final int LOGIN_TYPE_NONE = -1;
    public static final int LOGIN_TYPE_ADMIN = 0;
    public static final int LOGIN_TYPE_DELIVERY_BOY = 1;

    public static final int SELECTED_TYPE_SUPPLIER = 0;
    public static final int SELECTED_TYPE_PRODUCT = 1;
    public static final int SELECTED_TYPE_DELIVERY_BOY = 2;
    public static final int SELECTED_TYPE_CUSTOMER = 3;
}
