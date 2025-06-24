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
        MySqlCategoryDao dao = new MySqlCategoryDao(dataSource);

        // Act - Puts categories into a list and gets names from them using the stream loop
        List<Category> categories = dao.getAllCategories();
        List<String> names = categories.stream()
                .map(Category::getName)
                .toList();

        // Assert
        assertTrue(names.containsAll(List.of("Electronics", "Fashion", "Home & Kitchen")));
    }

    @Test
    public void getById_should_getProductByCategoryId() {
    }

    @Test
    public void create_should_makeANewCategory() {
    }

    @Test
    public void update_should_changeAnExistingCategory() {
    }

    @Test
    public void delete_should_removeAnExistingCategory() {
    }
}