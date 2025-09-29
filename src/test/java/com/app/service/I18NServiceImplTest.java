package com.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class I18NServiceImplTest {
    private I18nServiceImpl i18n;

    @BeforeEach
    void setup() {
        i18n = new I18nServiceImpl();
    }

    @Test
    void testInsertAndLoad() {
        i18n.insert("unit.test.key","EN","Unit Test EN");
        i18n.insert("unit.test.key","SK","Unit Test SK");
        i18n.load("EN");
        assertEquals("Unit Test EN", i18n.getKey("unit.test.key"));
        i18n.load("SK");
        assertEquals("Unit Test SK", i18n.getKey("unit.test.key"));
    }
}
