package com.app.service;

import com.app.model.Beekeeper;

import java.util.List;

public interface BeekeeperService {

    List<Beekeeper> getAll();

    Beekeeper getDetails(int id);

    void add(String firstName, String lastName, String address, String hobby);
}
