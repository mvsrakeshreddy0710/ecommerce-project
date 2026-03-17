package com.ecommerce.project.Controller;

import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.Service.CategoryService;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("/api") // Base URL for all endpoints in this controller (every API starts with /api)

public class CategoryController {

    @Autowired
    private CategoryService categoryService; // Injects CategoryService so we can use its methods

    //  (Alternative shorter mapping annotation)
//    @GetMapping("/echo") // For learning Purpose how to pass params.
//    public ResponseEntity<String> echoMessage(@RequestParam(name = "message", required = false) String message) {
//        //public ResponseEntity<String> echoMessage(@RequestParam(name = "message", defaultValue = "Hello World!") String message) {
//
//            return new ResponseEntity<>("Echoed message :"+ message, HttpStatus.OK);
//    }
    @RequestMapping(value = "/public/categories", method = RequestMethod.GET)
    // Handles GET request to /api/public/categories
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize",defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageSize,
            @RequestParam(name = "sortBy",defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder)
     {
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
        // Calls service layer to fetch all categories from database

        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
        // Returns category list with HTTP status 200 (Success)
    }

    @RequestMapping(value = "/public/categories", method = RequestMethod.POST)
    // Handles POST request to /api/public/categories
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
       CategoryDTO savedCategoryDTO= categoryService.createCategory(categoryDTO);
        // Takes JSON from request body and saves it using service layer

        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.CREATED);
        // Returns success message with HTTP status 201 (Created)
    }

    @RequestMapping(value = "/admin/categories/{categoryId}", method = RequestMethod.DELETE)
    // Handles DELETE request to /api/admin/categories/{categoryId}
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
        // @ PathVariable gets categoryId from URL

//        try {
        CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);
            // Calls service layer to delete category
            return new ResponseEntity<>(deletedCategory,HttpStatus.OK);

            // Returns success response with HTTP 200
//        } catch (ResponseStatusException e) {
//            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
//            // If error happens, return error message and proper HTTP status
//        }
    }

    @RequestMapping(value = "/public/categories/{categoryId}", method = RequestMethod.PUT)
    // Handles PUT request to /api/public/categories/{categoryId}
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                                 @PathVariable Long categoryId) {
        // Takes updated category data and categoryId from URL
//
//        try {
        CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO, categoryId);
            // Calls service layer to update category

            return new ResponseEntity<>( savedCategoryDTO,HttpStatus.OK);
            // Returns success message with HTTP 200
//        } catch (ResponseStatusException e) {
//            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
//            // Returns error message if category not found or other issue
//        }
    }
}