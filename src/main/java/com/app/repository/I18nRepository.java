package com.app.repository;

import java.util.Map;

public interface I18nRepository {
    Map<String, String> loadLocale(String locale);

    void insertTranslation(String key, String locale, String value);
}
