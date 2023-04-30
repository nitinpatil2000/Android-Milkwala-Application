package com.technosoul.milkwala.accounts.auth;

public interface ITokenStore {
    String get(String key);
    void set(String key, String value);
}
