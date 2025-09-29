package com.app.repository;

import com.app.config.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.util.HashMap;
import java.util.Map;

public class I18nRepositoryImpl implements I18nRepository {

    public I18nRepositoryImpl() {
        try (Connection conn = DriverManager.getConnection(AppConfig.getDatabaseUrl());
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS Translations (" +
                    "key TEXT NOT NULL," +
                    "locale TEXT NOT NULL," +
                    "value TEXT," +
                    "PRIMARY KEY (key, locale)" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, String> loadLocale(String locale) {
        Map<String, String> map = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(AppConfig.getDatabaseUrl());
             PreparedStatement ps = conn.prepareStatement("SELECT key,value FROM Translations WHERE locale=?")) {
            ps.setString(1, locale);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("key"), rs.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public void insertTranslation(String key, String locale, String value) {
        try (Connection conn = DriverManager.getConnection(AppConfig.getDatabaseUrl());
             PreparedStatement ps = conn.prepareStatement("INSERT OR REPLACE INTO Translations (key,locale,value) VALUES (?,?,?)")) {
            ps.setString(1, key);
            ps.setString(2, locale);
            ps.setString(3, value);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
