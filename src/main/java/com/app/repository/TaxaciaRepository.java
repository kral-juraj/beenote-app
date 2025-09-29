package com.app.repository;

import com.app.model.Taxacia;

import java.util.List;

public interface TaxaciaRepository {
    List<Taxacia> getAll();

    void insert(String date, String hive, String description);
}
