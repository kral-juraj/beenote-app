package com.app.service;

public interface I18nService {
    void load(String locale);

    String getKey(String key);

    void insert(String key, String locale, String value);
}
