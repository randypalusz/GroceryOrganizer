package com.app.services;

import com.app.enums.Location;
import com.app.models.PantryItem;
import com.app.services.messages.ServiceResponse;

import java.util.List;
import java.util.Optional;

public interface PantryItemService {

    ServiceResponse<PantryItem> addItem(PantryItem item);

    ServiceResponse<PantryItem> updateItem(PantryItem item);

    List<PantryItem> getAllItems();

    ServiceResponse<List<PantryItem>> getAllItemsWithLocation(Location location);
}
