package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

// This class has a REST controller annotation to deal with the API requests.
// Request mapping gives the general url directory for categories since all methods are pertaining to that.
// CrossOrigin Allows cross-origin requests for the front end
@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoriesController {

    // DAO properties for accessing and managing product/category data in the database
    private CategoryDao categoryDao;
    private ProductDao productDao;

    // Constructs the controller with injected DAOs for categories and products.
    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    // Method to return all categories using another method
    @GetMapping
    @PreAuthorize("permitAll()")
    public List<Category> getAll() {
        return categoryDao.getAllCategories();
    }

    // Method to get a single category based on ID
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public Category getById(@PathVariable int id) {
        return categoryDao.getById(id);
    }

    // Method to get all products that are in a single category
    @GetMapping("/{categoryId}/products")
    @PreAuthorize("permitAll()")
    public List<Product> getProductsById(@PathVariable int categoryId) {
        return productDao.listByCategoryId(categoryId);
    }

    // Method to make a new category (requires ADMIN role)
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Category addCategory(@RequestBody Category category) {
        return categoryDao.create(category);
    }

    // Method to update an existing category (requires ADMIN role)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateCategory(@PathVariable int id, @RequestBody Category category) {
        categoryDao.update(id, category);
    }

    // Method to delete an existing category (requires ADMIN role)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCategory(@PathVariable int id) {
        categoryDao.delete(id);
    }
}