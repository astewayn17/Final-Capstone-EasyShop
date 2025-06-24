package org.yearup.data.mysql;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MySqlProductDaoTest extends BaseDaoTestClass {

    private MySqlProductDao dao;

    @BeforeEach
    public void setup() {
        dao = new MySqlProductDao(dataSource);
        // Arrange - making reusable category 4 and products for the tests below
        CategoryDao categoryDao = new MySqlCategoryDao(dataSource);
        Category cat4 = new Category(4, "School Stuff", "Simple school stuff");
        categoryDao.create(cat4);
        Product eraser = new Product(100, "Eraser", new BigDecimal(5.99), 4,
                "Simple eraser", "Pink", 30, false, "smartphone.jpg");
        Product pencil = new Product(101, "Pencil", new BigDecimal(1.99), 4,
                "Simple pencil", "Yellow", 30, false, "smartphone.jpg");
        Product pen = new Product(102, "Pen", new BigDecimal(3.99), 4,
                "Simple pen", "Black", 30, false, "smartphone.jpg");
        dao.create(eraser);
        dao.create(pencil);
        dao.create(pen);
    }

    @Test
    public void search_shouldReturn_theCorrectProduct() {
        // Arrange - In BeforeEach

        // Act
        List<Product> result = dao.search(4, new BigDecimal("1.00"), new BigDecimal("5.00"), "Black");

        // Assert
        assertEquals("Pen", result.get(0).getName());
    }

    @Test
    public void listByCategoryId_shouldReturn_correspondingProducts() {
        // Arrange - In BeforeEach

        // Act
        List<Product> result = dao.listByCategoryId(4);

        // Assert - Gets the names of the products as a list using the stream
        List<String> names = result.stream().map(Product::getName).toList();
        assertEquals(List.of("Eraser", "Pencil", "Pen"), names);
    }

    @Test
    public void getById_shouldReturn_theCorrectProduct() {
        // Arrange
        int productId = 1;
        Product expected = new Product()
        {{
            setProductId(1);
            setName("Smartphone");
            setPrice(new BigDecimal("499.99"));
            setCategoryId(1);
            setDescription("A powerful and feature-rich smartphone for all your communication needs.");
            setColor("Black");
            setStock(50);
            setFeatured(false);
            setImageUrl("smartphone.jpg");
        }};

        // Act
        var actual = dao.getById(productId);

        // Assert
        assertEquals(expected.getPrice(), actual.getPrice(), "Because I tried to get product 1 from the database.");
    }

    @Test
    public void create_shouldReturn_theCreatedProduct() {
        // Arrange
        Product notebook = new Product(103, "Notebook", new BigDecimal(7.99), 4,
                "Spiral notebook", "Blue", 20, false, "notebook.jpg");

        // Act
        dao.create(notebook);
        String name = notebook.getName();

        // Assert
        assertEquals("Notebook", name);
    }

    @Test
    public void update_shouldReturn_theUpdatedProduct() {
        // Arrange - In BeforeEach

        // Act

        // Assert
    }

    @Test
    public void delete_shouldDelete_theCorrespondingProduct() {
        // Arrange - In BeforeEach

        // Act

        // Assert
    }
}