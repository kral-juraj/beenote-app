package com.app.service;

import com.app.model.Taxacia;
import com.app.repository.TaxaciaRepositoryImpl;

import java.util.List;

public class TaxaciaServiceImpl implements TaxaciaService {
    private final TaxaciaRepositoryImpl repo;

    public TaxaciaServiceImpl() {
        this.repo = new TaxaciaRepositoryImpl();
    }

    @Override
    public List<Taxacia> getAll() {
        return repo.getAll();
    }

    @Override
    public void add(String datum, String ul, String poznamky) {
        repo.insert(datum, ul, poznamky);
    }
}
