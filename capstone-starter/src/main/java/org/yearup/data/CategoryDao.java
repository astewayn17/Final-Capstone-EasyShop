package org.yearup.data;

import org.yearup.models.Category;

import java.util.List;

// Data Access Object (DAO) interface for managing categories.
// Provides methods to create, retrieve, update, and delete category records.
public interface CategoryDao {
    List<Category> getAllCategories();
    Category getById(int categoryId);
    Category create(Category category);
    void update(int categoryId, Category category);
    void delete(int categoryId);
}