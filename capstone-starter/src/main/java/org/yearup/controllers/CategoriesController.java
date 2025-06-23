package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("categories")
@CrossOrigin
public class CategoriesController {

    private CategoryDao categoryDao;
    private ProductDao productDao;

    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @GetMapping
    public List<Category> getAll() {
        return categoryDao.getAllCategories();
    }

    @GetMapping("{id}")
    public Category getById(@PathVariable int id) {
        return categoryDao.getById(id);
    }

    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId) {
        return productDao.listByCategoryId(categoryId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Category addCategory(@RequestBody Category category) {
        return categoryDao.create(category);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateCategory(@PathVariable int id, @RequestBody Category category) {
        categoryDao.update(id, category);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCategory(@PathVariable int id)
    {
        categoryDao.delete(id);
    }
}

//
//        @GetMapping("{id}")
//        @PreAuthorize("permitAll()")
//        public Product getById(@PathVariable int id ) {
//            try {
//                var product = productDao.getById(id);
//
//                if(product == null)
//                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//
//                return product;
//            } catch(Exception ex) {
//                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
//            }
//        }
//
//        @PostMapping()
//        @PreAuthorize("hasRole('ROLE_ADMIN')")
//        public Product addProduct(@RequestBody Product product) {
//            try {
//                return productDao.create(product);
//            } catch(Exception ex) {
//                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
//            }
//        }
//
//        @PutMapping("{id}")
//        @PreAuthorize("hasRole('ROLE_ADMIN')")
//        public void updateProduct(@PathVariable int id, @RequestBody Product product) {
//            try {
//                /// This was the main issue with the REST api failing to update an existing product.
//                /// It was instead creating a new product because it used the create() method.
//                productDao.update(id, product);
//            } catch(Exception ex) {
//                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
//            }
//        }
//
//        @DeleteMapping("{id}")
//        @PreAuthorize("hasRole('ROLE_ADMIN')")
//        public void deleteProduct(@PathVariable int id) {
//            try {
//                var product = productDao.getById(id);
//
//                if(product == null)
//                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//
//                productDao.delete(id);
//            } catch(Exception ex) {
//                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
//            }
//        }