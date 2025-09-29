package com.app.service;

import com.app.model.Beekeeper;
import com.app.repository.BeekeeperRepositoryImpl;

import java.util.List;

public class BeekeeperServiceImpl implements BeekeeperService {
    private final BeekeeperRepositoryImpl repo;

    public BeekeeperServiceImpl() {
        this.repo = new BeekeeperRepositoryImpl();
    }

    @Override
    public List<Beekeeper> getAll() {
        return repo.getAll();
    }

    @Override
    public Beekeeper getDetails(int id) {
        return repo.getById(id);
    }

    @Override
    public void add(String firstName, String lastName, String address, String hobby) {
        repo.insert(firstName, lastName, address, hobby);
    }
}
