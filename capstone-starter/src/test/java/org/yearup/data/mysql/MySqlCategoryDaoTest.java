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
        Category expected = new Category(4, "Toys", "Children toys");

        // Act - Will make the category and then loop through it acquiring the names of them
        categoryDao.create(expected);
        List<String> names = categoryDao.getAllCategories()
                .stream()
                .map(Category::getName)
                .toList();

        // Assert
        assertTrue(names.contains("Toys"));
    }

    @Test
    public void getById_should_getCategoryById() {
        // Arrange
        MySqlCategoryDao categoryDao = new MySqlCategoryDao(dataSource);
        Category newCategory = new Category(4, "Gardening", "Tools for gardening");

        // Act
        categoryDao.create(newCategory);
        Category result = categoryDao.getById(4);

        // Assert
        assertEquals("Gardening", result.getName());
    }

    @Test
    public void create_should_makeANewCategory() {
        // Arrange
        MySqlCategoryDao categoryDao = new MySqlCategoryDao(dataSource);
        Category newCategory = new Category(4, "Auto", "Car accessories and parts");

        // Act
        categoryDao.create(newCategory);
        Category result = categoryDao.getById(4);

        // Assert
        assertEquals("Auto", result.getName());
    }
}