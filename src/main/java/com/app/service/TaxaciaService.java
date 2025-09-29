package com.app.service;

import com.app.model.Taxacia;

import java.util.List;

public interface TaxaciaService {

    List<Taxacia> getAll();

    void add(String datum, String ul, String poznamky);
}
