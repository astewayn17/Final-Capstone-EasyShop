package org.yearup.data.mysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.yearup.data.ProductDao;
import org.yearup.models.ShoppingCart;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MySqlShoppingCartDaoTest extends BaseDaoTestClass {

    private MySqlShoppingCartDao shoppingCartDao;
    private ProductDao productDao;

    @BeforeEach
    public void setUp() {
        productDao = new MySqlProductDao(dataSource);
        shoppingCartDao = new MySqlShoppingCartDao(dataSource, productDao);
    }

    @Test
    public void getByUserId_should_returnShoppingCartWithItems() {
        // Arrange
        shoppingCartDao.addItemToCart(1, 1, 2);
        // Act
        ShoppingCart cart = shoppingCartDao.getByUserId(1);
        // Assert
        assertEquals(1, cart.getItems().size());
    }

    @Test
    public void addItemToCart_should_addItemToCart() {
        // Act
        shoppingCartDao.addItemToCart(1, 1, 3);
        // Assert
        assertTrue(shoppingCartDao.itemExists(1, 1));
    }

    @Test
    public void updateItemInCart_should_updateItemQuantity() {
        // Arrange
        shoppingCartDao.addItemToCart(1, 1, 2);
        // Act
        shoppingCartDao.updateItemInCart(1, 1, 5);
        // Assert
        assertEquals(5, shoppingCartDao.getItemQuantity(1, 1));
    }

    @Test
    public void clearCart_should_removeAllItemsFromCart() {
        // Arrange
        shoppingCartDao.addItemToCart(1, 1, 2);
        // Act
        shoppingCartDao.clearCart(1);
        // Assert
        assertFalse(shoppingCartDao.itemExists(1, 1));
    }

    @Test
    public void itemExists_should_returnTrueWhenItemExists() {
        // Arrange
        shoppingCartDao.addItemToCart(1, 1, 2);
        // Act
        boolean exists = shoppingCartDao.itemExists(1, 1);
        // Assert
        assertTrue(exists);
    }

    @Test
    public void getItemQuantity_should_returnCorrectQuantity() {
        // Arrange
        shoppingCartDao.addItemToCart(1, 1, 3);
        // Act
        int quantity = shoppingCartDao.getItemQuantity(1, 1);
        // Assert
        assertEquals(3, quantity);
    }
}