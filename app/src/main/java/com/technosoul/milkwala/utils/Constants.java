package com.technosoul.milkwala.utils;

import com.technosoul.milkwala.BuildConfig;

public class Constants {

    private static final String PKG = BuildConfig.APPLICATION_ID;
    public static final String SHARED_PREF_NAME = "MilkwalaSharedPreference";
    public static final String KEY_LOGIN_TYPE = PKG + ".login_type";
    public static final int LOGIN_TYPE_NONE = -1;
    public static final int LOGIN_TYPE_ADMIN = 0;
    public static final int LOGIN_TYPE_DELIVERY_PERSON = 1;

    public static final int SELECTED_TYPE_SUPPLIER = 0;
    public static final int SELECTED_TYPE_PRODUCT = 1;
    public static final int SELECTED_TYPE_DELIVERY_PERSON = 2;
    public static final int SELECTED_TYPE_CUSTOMER = 3;
    public static final int SELECTED_SUPPLIER_FOR_PRODUCT_LIST = 4;

    public static final String UNIT_LITRE_1 = "1 Ltr";
    public static final String UNIT_LITRE_2 = "2 Ltr";
    public static final String UNIT_ML_500 = "500 ml";
    public static final String UNIT_ML_250 = "250 ml";
    public static final String UNIT_ML_100 = "100 ml";
    public static final String UNIT_KG_1 = "1 kg";
    public static final String UNIT_KG_2 = "2 kg";
    public static final String UNIT_GRAM_500 = "500 gm";
    public static final String UNIT_GRAM_250 = "250 gm";
    public static final String UNIT_GRAM_200 = "200 gm";
    public static final String UNIT_GRAM_100 = "100 gm";
    public static final String UNIT_GRAM_50 = "50 gm";
    public static final String UNIT_PACKETS = "packets";

}
