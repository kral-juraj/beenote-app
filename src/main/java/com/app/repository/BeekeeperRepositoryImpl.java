package com.app.repository;

import com.app.config.AppConfig;
import com.app.model.Beekeeper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

public class BeekeeperRepositoryImpl implements BeekeeperRepository {

    public BeekeeperRepositoryImpl() {
        try (Connection conn = DriverManager.getConnection(AppConfig.getDatabaseUrl())) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS Beekeeper ( " +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "Meno TEXT," +
                        "Priezvisko TEXT," +
                        "Adresa TEXT," +
                        "Hobby TEXT)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Beekeeper> getAll() {
        List<Beekeeper> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(AppConfig.getDatabaseUrl());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Beekeeper")) {

            while (rs.next()) {
                list.add(new Beekeeper(
                        rs.getInt("ID"),
                        rs.getString("Meno"),
                        rs.getString("Priezvisko"),
                        rs.getString("Adresa"),
                        rs.getString("Hobby")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Beekeeper getById(int id) {
        try (Connection conn = DriverManager.getConnection(AppConfig.getDatabaseUrl());
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Beekeeper WHERE ID=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Beekeeper(
                        rs.getInt("ID"),
                        rs.getString("Meno"),
                        rs.getString("Priezvisko"),
                        rs.getString("Adresa"),
                        rs.getString("Hobby"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(String firstName, String lastName, String address, String hobby) {
        try (Connection conn = DriverManager.getConnection(AppConfig.getDatabaseUrl());
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Beekeeper (Meno,Priezvisko,Adresa,Hobby) VALUES (?,?,?,?)")) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, address);
            ps.setString(4, hobby);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
