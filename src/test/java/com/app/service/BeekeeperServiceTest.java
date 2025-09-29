package com.app.service;

import com.app.model.Beekeeper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BeekeeperServiceTest {
    private BeekeeperServiceImpl service;

    @BeforeEach
    void setup() {
        service = new BeekeeperServiceImpl();
    }

    @Test
    void testAddAndRetrieve() {
        service.add("Test","User","City","Hobby");
        List<Beekeeper> all = service.getAll();
        assertFalse(all.isEmpty());
        Beekeeper last = all.get(all.size()-1);
        Beekeeper detail = service.getDetails(last.getId());
        assertEquals("Test", detail.getFirstName());
        assertEquals("User", detail.getLastName());
    }
}
