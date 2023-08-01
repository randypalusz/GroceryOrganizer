package com.app.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.app.enums.Location;
import com.app.services.PantryItemService;
import com.app.enums.Status;
import com.app.services.messages.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.app.errors.ApiError;
import com.app.models.PantryItem;
import com.app.services.RandomGeneratorService;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;

    @Autowired
    ApplicationContext context;

    RandomGeneratorService<Integer> randomGeneratorService;

    // TODO: make a PantryItemService that will have PantryItemRepository as a field,
    // call these service operations instead of the repository directly from here.
    // PantryItemRepository pantryItemRepository;
    PantryItemService pantryItemService;

    @Autowired
    SimpleController(PantryItemService pantryItemService, RandomGeneratorService<Integer> randomGeneratorService) {
        this.pantryItemService = pantryItemService;
        this.randomGeneratorService = randomGeneratorService;
    }

    // TODO: move this to a @ControllerAdvice annotated class
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiError handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        return new ApiError(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/")
    public String homePage(Model model) {
        final String templateName = "home";
        model.addAttribute("appName", appName);
        model.addAttribute("items", pantryItemService.getAllItems());
        model.addAttribute("label", randomGeneratorService.getLabel());
        return templateName;
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<PantryItem> getAllPantryItems() {
        return pantryItemService.getAllItems();
    }

    @GetMapping("/getAllWithLocation")
    @ResponseBody
    public ResponseEntity<Object> getAllPantryItemsWithLocation(@RequestParam(name = "location") Location location) {
        ServiceResponse<List<PantryItem>> response = pantryItemService.getAllItemsWithLocation(location);
        switch (response.status) {
            default -> {
                return ResponseEntity.status(HttpStatus.OK).body(response.val);
            }
        }
    }

    @PostMapping(value = "/item", consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> addItem(@RequestBody PantryItem pantryItem) {
        ServiceResponse<PantryItem> response = pantryItemService.addItem(pantryItem);
        if (response.status != Status.SUCCESS) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(response.errorMessage, HttpStatus.BAD_REQUEST));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response.val);
    }

}
