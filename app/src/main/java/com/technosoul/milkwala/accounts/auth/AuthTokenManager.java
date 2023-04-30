package com.technosoul.milkwala.accounts.auth;

import com.technosoul.milkwala.BuildConfig;

public class AuthTokenManager {

    /**
     * Holder class to store the instance of the outer class, dur to Java's class loading
     * mechanism it is instantiated only when it is accessed.
     */
    private static class Holder {
        static final AuthTokenManager INSTANCE = new AuthTokenManager();
    }

    private static final String KEY_TOKEN = BuildConfig.APPLICATION_ID + ".token";

    public static synchronized AuthTokenManager instance() {
        return Holder.INSTANCE;
    }

    private ITokenStore mStore;

    private AuthTokenManager() {
        mStore = new TokenStore();
    }

    public String get() {
        return mStore.get(KEY_TOKEN);
    }

    public void set(String token) {
        mStore.set(KEY_TOKEN, token);
    }
}
