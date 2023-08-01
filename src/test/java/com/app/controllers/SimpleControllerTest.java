package com.app.controllers;

import com.app.enums.Location;
import com.app.errors.ApiError;
import com.app.repositories.PantryItemRepository;
import com.app.services.PantryItemService;
import com.app.services.PantryItemServiceImpl;
import com.app.services.RandomGeneratorServiceImpl;
import com.app.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.app.models.PantryItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

// TODO: need to mock the findByName call in each test
@ExtendWith(MockitoExtension.class)
public class SimpleControllerTest {

    @Mock
    private PantryItemRepository repository;
    private PantryItemService pantryItemService;
    private final RandomGeneratorServiceImpl service = new RandomGeneratorServiceImpl();
    private SimpleController controller;

    @BeforeEach
    public void beforeEach() {
        pantryItemService = new PantryItemServiceImpl(repository);
        controller = new SimpleController(pantryItemService, service);
    }

    @Test
    public void addItemBasicTest() {
        PantryItem item = new PantryItem();
        item.setName("item2");
        item.setQuantity(3);

        Mockito.when(repository.findByName(Mockito.isA(String.class))).thenReturn(new ArrayList<>());
        Mockito.when(repository.save(Mockito.eq(item))).thenReturn(item);

        ResponseEntity<Object> responseEntity = controller.addItem(item);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(item, responseEntity.getBody());
    }

    @Test
    public void addItemDuplicateNameUniqueLocation() {
        final String NAME = "duplicate_item";
        PantryItem item1 = new PantryItem();
        item1.setName(NAME);
        item1.setQuantity(3);
        item1.setLocation(Location.REFRIGERATOR);

        PantryItem item2 = new PantryItem();
        item2.setName(NAME);
        item2.setQuantity(1);
        item2.setLocation(Location.FREEZER);

        // Mock the behavior
        Mockito.when(repository.findByName(NAME)).thenReturn(new ArrayList<>()).thenReturn(List.of(item1));
        // Assume the method returns the same arg as passed in each time
        Mockito.when(repository.save(Mockito.isA(PantryItem.class))).then(returnsFirstArg());

        controller.addItem(item1);
        ResponseEntity<Object> response = controller.addItem(item2);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        // assertEquals(new ApiError("Duplicate Item Exists", HttpStatus.BAD_REQUEST), response.getBody());

    }

    @Test
    public void addItemErrorDuplicateNameSameLocation() {
        final String NAME = "duplicate_item";
        PantryItem item1 = new PantryItem();
        item1.setName(NAME);
        item1.setQuantity(3);
        item1.setLocation(Location.REFRIGERATOR);

        PantryItem item2 = new PantryItem();
        item2.setName(NAME);
        item2.setQuantity(1);
        item2.setLocation(Location.REFRIGERATOR);

        // Mock the behavior
        Mockito.when(repository.findByName(NAME)).thenReturn(new ArrayList<>()).thenReturn(List.of(item1));
        // Assume the method returns the same arg as passed in each time
        Mockito.when(repository.save(Mockito.isA(PantryItem.class))).then(returnsFirstArg());

        controller.addItem(item1);
        ResponseEntity<Object> response = controller.addItem(item2);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(new ApiError(Status.DUPLICATE_EXISTS.getMessage(), HttpStatus.BAD_REQUEST), response.getBody());
    }

}
