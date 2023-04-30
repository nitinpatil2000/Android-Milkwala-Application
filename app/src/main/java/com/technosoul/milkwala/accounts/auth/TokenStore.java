package com.technosoul.milkwala.accounts.auth;

import com.technosoul.milkwala.MilkwalaApp;

public class TokenStore implements ITokenStore {
    @Override
    public String get(String key) {
        return MilkwalaApp.getSharedPreference().getString(key, null);
    }

    @Override
    public void set(String key, String value) {
        MilkwalaApp.getSharedPreference().edit().putString(key, value).apply();
    }
}
