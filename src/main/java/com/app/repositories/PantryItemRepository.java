package com.app.repositories;

import com.app.enums.Location;
import com.app.models.PantryItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PantryItemRepository extends CrudRepository<PantryItem, String> {
    List<PantryItem> findByName(String name);

    List<PantryItem> findByQuantity(String quantity);

    List<PantryItem> findByLocation(Location location);
}
