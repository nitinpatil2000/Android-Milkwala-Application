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

    public static final String UNIT_LITRE_1 = "UNIT_LITRE_1";
    public static final String UNIT_LITRE_2 = "UNIT_LITRE_2";
    public static final String UNIT_ML_500 = "UNIT_ML_500";
    public static final String UNIT_ML_250 = "UNIT_ML_250";
    public static final String UNIT_ML_100 = "UNIT_ML_100";
    public static final String UNIT_KG_1 = "UNIT_KG_1";
    public static final String UNIT_KG_2 = "UNIT_KG_2";
    public static final String UNIT_GRAM_500 = "UNIT_GRAM_500";
    public static final String UNIT_GRAM_250 = "UNIT_GRAM_250";
    public static final String UNIT_GRAM_200 = "UNIT_GRAM_200";
    public static final String UNIT_GRAM_100 = "UNIT_GRAM_100";
    public static final String UNIT_GRAM_50 = "UNIT_GRAM_50";
    public static final String UNIT_PACKETS = "UNIT_PACKETS";
    public static final String CARET = "CARET";


    public static final String MILK = "MILK";
    public static final String BUTTERMILK = "BUTTERMILK";
    public static final String PANEER = "PANEER";
    public static final String GHEE = "GHEE";
    public static final String BUTTER = "BUTTER";
    public static final String CURD = "CURD";


    public static final String  RETAILER = "RETAILER";
    public static final String WHOLESALE = "WHOLESALE";



}
