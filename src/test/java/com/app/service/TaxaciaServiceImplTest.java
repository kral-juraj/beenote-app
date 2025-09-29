package com.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaxaciaServiceImplTest {
    private TaxaciaServiceImpl service;

    @BeforeEach
    void setup() {
        service = new TaxaciaServiceImpl();
    }

    @Test
    void testAddAndList() {
        service.add("2025-09-01","UL-1","No notes");
        List all = service.getAll();
        assertFalse(all.isEmpty());
    }
}
