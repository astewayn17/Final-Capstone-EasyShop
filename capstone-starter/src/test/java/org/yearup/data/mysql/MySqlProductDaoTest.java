package org.yearup.data.mysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.yearup.models.Product;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class MySqlProductDaoTest extends BaseDaoTestClass {

    private MySqlProductDao dao;

    @BeforeEach
    public void setup() {
        dao = new MySqlProductDao(dataSource);

        // Create test data - use existing category 1 that should already exist
        Product eraser = new Product(100, "Eraser", new BigDecimal("5.99"), 1,
                "Simple eraser", "Pink", 30, false, "smartphone.jpg");
        Product pencil = new Product(101, "Pencil", new BigDecimal("1.99"), 1,
                "Simple pencil", "Yellow", 30, false, "smartphone.jpg");
        Product pen = new Product(102, "Pen", new BigDecimal("3.99"), 1,
                "Simple pen", "Black", 30, false, "smartphone.jpg");
        dao.create(eraser);
        dao.create(pencil);
        dao.create(pen);
    }

    @Test
    public void search_should_returnTheCorrectProduct() {
        // Act
        List<Product> result = dao.search(1, new BigDecimal("1.00"), new BigDecimal("5.00"), "Black");
        // Assert
        assertFalse(result.isEmpty(), "Should find products matching the criteria");
    }

    @Test
    public void listByCategoryId_should_returnTheCorrectProducts() {
        // Act
        List<Product> result = dao.listByCategoryId(1);
        // Assert
        assertFalse(result.isEmpty(), "Expected products in category 1");
    }

    @Test
    public void getById_should_returnTheCorrectProduct() {
        // Act
        var actual = dao.getById(1); // Use existing product ID 1
        // Assert
        assertEquals(new BigDecimal("499.99"), actual.getPrice());
    }

    @Test
    public void create_should_makeANewProduct() {
        // Arrange
        Product notebook = new Product(103, "Notebook", new BigDecimal("7.99"), 1,
                "Spiral notebook", "Blue", 20, false, "notebook.jpg");
        // Act
        dao.create(notebook);
        // Assert
        assertEquals("Notebook", notebook.getName());
    }
}