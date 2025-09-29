package com.app.repository;

import com.app.config.AppConfig;
import com.app.model.Taxacia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class TaxaciaRepositoryImpl implements TaxaciaRepository {

    public TaxaciaRepositoryImpl() {
        try (Connection conn = DriverManager.getConnection(AppConfig.getDatabaseUrl())) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS Taxation (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "date TEXT," +
                        "hive TEXT," +
                        "description TEXT)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Taxacia> getAll() {
        List<Taxacia> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(AppConfig.getDatabaseUrl());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Taxation")) {

            while (rs.next()) {
                list.add(new Taxacia(
                        rs.getInt("ID"),
                        rs.getString("date"),
                        rs.getString("hive"),
                        rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void insert(String date, String hive, String description) {
        try (Connection conn = DriverManager.getConnection(AppConfig.getDatabaseUrl());
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Taxation (date,hive,description) VALUES (?,?,?)")) {
            ps.setString(1, date);
            ps.setString(2, hive);
            ps.setString(3, description);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
