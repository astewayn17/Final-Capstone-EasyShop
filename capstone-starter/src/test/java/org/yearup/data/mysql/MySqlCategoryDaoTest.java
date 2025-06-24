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

        // Act
        categoryDao.create(expected);
        Category actual = categoryDao.getById(4);

        // Assert
        assertEquals("Toys", actual.getName());
    }

    @Test
    public void getById_should_getCategoryById() {

    }

    @Test
    public void create_should_makeANewCategory() {

    }
}