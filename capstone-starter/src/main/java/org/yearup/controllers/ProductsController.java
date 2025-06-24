package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.models.Product;
import org.yearup.data.ProductDao;

import java.math.BigDecimal;
import java.util.List;

// This class has a REST controller annotation to deal with the API requests.
// Request mapping gives the general url directory for categories since all methods are pertaining to that.
// CrossOrigin Allows cross-origin requests for the front end
@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductsController {

    // DAO property for accessing and managing product data in the database
    private ProductDao productDao;

    // Constructs the ProductsController with a ProductDao dependency.
    @Autowired
    public ProductsController(ProductDao productDao) {
        this.productDao = productDao;
    }

    // Searches for products based on optional filter criteria such as category, price range, and color.
    // Accessible to all users (no authentication required).
    @GetMapping
    @PreAuthorize("permitAll()")
    public List<Product> search(@RequestParam(name="cat", required = false) Integer categoryId,
                                @RequestParam(name="minPrice", required = false) BigDecimal minPrice,
                                @RequestParam(name="maxPrice", required = false) BigDecimal maxPrice,
                                @RequestParam(name="color", required = false) String color) {
        try {
            return productDao.search(categoryId, minPrice, maxPrice, color);
        } catch(Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    // Method to get a single product based on ID
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public Product getById(@PathVariable int id ) {
        try {
            var product = productDao.getById(id);
            if(product == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            return product;
        } catch(Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    // Method to make a new product (requires ADMIN role)
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Product addProduct(@RequestBody Product product) {
        try {
            return productDao.create(product);
        } catch(Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    // Method to update an existing product (requires ADMIN role)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateProduct(@PathVariable int id, @RequestBody Product product) {
        try {
            /// This was the main issue with the REST api failing to update an existing product.
            /// It was instead creating a new product because it used the create() method.
            productDao.update(id, product);
        } catch(Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    // Method to delete an existing product (requires ADMIN role)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProduct(@PathVariable int id) {
        try {
            var product = productDao.getById(id);

            if(product == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            productDao.delete(id);
        } catch(Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}