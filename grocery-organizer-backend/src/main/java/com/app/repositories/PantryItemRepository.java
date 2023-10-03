package com.app.repositories;

import com.app.enums.Location;
import com.app.models.PantryItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PantryItemRepository extends CrudRepository<PantryItem, String> {
    PantryItem getReferenceById(String id);

    List<PantryItem> findByName(String name);

    List<PantryItem> findByQuantity(String quantity);

    PantryItem findFirstByNameAndLocation(String name, Location location);

    List<PantryItem> findByLocation(Location location);
}
