package org.yearup.data.mysql;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.yearup.models.Category;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MySqlCategoryDaoTest extends BaseDaoTestClass {

    @Test
    public void getAllCategories_should_getAllCategoryNames() {
        // Arrange
        MySqlCategoryDao categoryDao = new MySqlCategoryDao(dataSource);
        Category expected = new Category(10, "Toys", "Children toys");
        // Act
        categoryDao.create(expected);
        List<Category> categories = categoryDao.getAllCategories();
        // Assert
        assertFalse(categories.isEmpty());
    }

    @Test
    public void getById_should_getCategoryById() {
        // Arrange
        MySqlCategoryDao categoryDao = new MySqlCategoryDao(dataSource);
        Category newCategory = new Category(11, "Gardening", "Tools for gardening");
        // Act
        Category created = categoryDao.create(newCategory);
        Category result = categoryDao.getById(created.getCategoryId());
        // Assert
        assertEquals("Gardening", result.getName());
    }

    @Test
    public void create_should_makeANewCategory() {
        // Arrange
        MySqlCategoryDao categoryDao = new MySqlCategoryDao(dataSource);
        Category newCategory = new Category(12, "Auto", "Car accessories and parts");
        // Act
        Category created = categoryDao.create(newCategory);
        // Assert
        assertEquals("Auto", created.getName());
    }
}