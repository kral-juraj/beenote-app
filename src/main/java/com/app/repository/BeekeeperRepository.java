package com.app.repository;


import com.app.model.Beekeeper;


import java.util.List;

public interface BeekeeperRepository {
    List<Beekeeper> getAll();

    Beekeeper getById(int id);

    void insert(String firstName, String lastName, String address, String hobby);
}
