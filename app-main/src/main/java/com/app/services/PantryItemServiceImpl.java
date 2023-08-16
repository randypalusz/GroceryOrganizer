package com.app.services;

import com.app.enums.Location;
import com.app.enums.Status;
import com.app.models.PantryItem;
import com.app.repositories.PantryItemRepository;
import com.app.services.messages.ServiceResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PantryItemServiceImpl implements PantryItemService {
    PantryItemRepository repository;

    @Autowired
    public PantryItemServiceImpl(PantryItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public ServiceResponse<PantryItem> addItem(PantryItem item) {
        // Get all pantry items that share the ID
        List<PantryItem> entriesWithSameName = repository.findByName(item.getName());
        // retain only those that exist in the same location as the input
        // TODO: assess whether it's better to put this duplicate name/location checking
        // in here or as a DB call
        entriesWithSameName = entriesWithSameName.stream().filter(i -> i.getLocation().equals(item.getLocation())).toList();
        if (entriesWithSameName.size() > 0) {
            return new ServiceResponse<>(null, Status.DUPLICATE_EXISTS);
        }
        return new ServiceResponse<>(repository.save(item), Status.SUCCESS);
    }

    @Override
    public ServiceResponse<PantryItem> updateItem(PantryItem item) {
        PantryItem pantryItem;
        try {
            pantryItem = repository.findFirstByNameAndLocation(item.getName(), item.getLocation());
            if (pantryItem == null) {
                return new ServiceResponse<>(null, Status.NO_ITEM_EXISTS);
            }
        } catch (EntityNotFoundException e) {
            return new ServiceResponse<>(null, Status.NO_ITEM_EXISTS);
        }
        pantryItem.setQuantity(pantryItem.getQuantity() + item.getQuantity());
        repository.save(pantryItem);
        return new ServiceResponse<>(pantryItem, Status.SUCCESS);
    }

    @Override
    public List<PantryItem> getAllItems() {
        List<PantryItem> ret = new ArrayList<>();
        Iterable<PantryItem> things = repository.findAll();
        things.forEach(ret::add);
        return ret;
    }

    @Override
    public ServiceResponse<List<PantryItem>> getAllItemsWithLocation(Location location) {
        List<PantryItem> items = repository.findByLocation(location);
        if (!items.isEmpty()) {
            return new ServiceResponse<>(items, Status.SUCCESS);
        } else {
            return new ServiceResponse<>(items, Status.OTHER_ERROR);
        }
    }
}
