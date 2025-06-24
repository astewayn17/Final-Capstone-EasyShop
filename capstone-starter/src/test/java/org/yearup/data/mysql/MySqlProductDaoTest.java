package org.yearup.data.mysql;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.yearup.models.Product;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MySqlProductDaoTest extends BaseDaoTestClass {

    private MySqlProductDao dao;

    @BeforeEach
    public void setup() {
        dao = new MySqlProductDao(dataSource);
    }

    @Test
    public void search_shouldReturn_theCorrectProduct() {
        // Arrange
        Product eraser = new Product(100, "Eraser", new BigDecimal(5.99), 1,
                "Simple eraser", "Pink", 30, false, "smartphone.jpg");
        Product pencil = new Product(101, "Pencil", new BigDecimal(1.99), 2,
                "Simple pencil", "Yellow", 30, false, "smartphone.jpg");
        Product pen = new Product(102, "Pen", new BigDecimal(3.99), 3,
                "Simple pen", "Black", 30, false, "smartphone.jpg");
        dao.create(eraser);
        dao.create(pencil);
        dao.create(pen);

        // Act
        List<Product> result = dao.search(3, new BigDecimal("1.00"), new BigDecimal("5.00"), "Black");

        // Assert
        assertEquals(1, result.size(), "Should return only the black pen in category 3 between $1.00 and $5.00");
        assertEquals("Pen", result.get(0).getName());
    }

    @Test
    public void listByCategoryId() {
        // Arrange

        // Act

        // Assert
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
    public void create() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void update() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void delete() {
        // Arrange

        // Act

        // Assert
    }
}