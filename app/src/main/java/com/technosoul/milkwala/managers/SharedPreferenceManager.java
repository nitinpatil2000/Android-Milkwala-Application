package com.technosoul.milkwala.managers;

import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private static SharedPreferenceManager instance;

    private SharedPreferences sharedPreferences;

    /**
     * Method is used to return singleton instance of SharedPreferenceManager class
     *
     * @return Singleton SharedPreferenceManager instance
     */
    public static SharedPreferenceManager getInstance() {
        if (instance == null) {
            instance = new SharedPreferenceManager();
        }
        return instance;
    }

    /**
     * Private Constructor
     */
    private SharedPreferenceManager() {}

    /**
     * Method is used to set the SharedPreference
     *
     * @param sharedPreference SharedPreference instance
     */
    public void setSharedPreference(SharedPreferences sharedPreference) {
        sharedPreferences = sharedPreference;
    }

    public void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }
    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }
    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void putInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }
    public int getInt(String key) {
        return sharedPreferences.getInt(key, -1);
    }
    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }
}
