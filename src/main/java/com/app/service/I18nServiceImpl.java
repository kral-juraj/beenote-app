package com.app.service;

import com.app.repository.I18nRepositoryImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class I18nServiceImpl implements I18nService {
    private final I18nRepositoryImpl repo = new I18nRepositoryImpl();
    private final Map<String, String> current = new ConcurrentHashMap<>();

    @Override
    public void load(String locale) {
        current.clear();
        current.putAll(repo.loadLocale(locale));
    }

    @Override
    public String getKey(String key) {
        return current.getOrDefault(key, key);
    }

    @Override
    public void insert(String key, String locale, String value) {
        repo.insertTranslation(key, locale, value);
    }
}
