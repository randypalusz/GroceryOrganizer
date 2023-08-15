package com.app;

import com.app.enums.Location;
import com.app.models.PantryItem;
import com.app.repositories.PantryItemRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class TestApplicationTests {

    @Autowired
    private PantryItemRepository repository;

    @Test
    void contextLoads(ApplicationContext context) {
        PantryItem item = new PantryItem();
        item.setId(UUID.randomUUID());
        item.setName("test_item");
        item.setQuantity(5);
        item.setLocation(Location.PANTRY);
        repository.save(item);

        PantryItem item2 = repository.findByName("test_item").get(0);

        assertEquals(item, item2);
        assertNotNull(context);
    }

}
