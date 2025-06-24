package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    // DAO to get the product details
    private ProductDao productDao;

    // Constructor with injected dependencies DataSource for DB connection and ProductDao for product lookup
    @Autowired
    public MySqlShoppingCartDao(DataSource dataSource, ProductDao productDao) {
        super(dataSource);
        this.productDao = productDao;
    }

    // Query method to get the shopping cart for a user by getting cart items and returning them with their details
    @Override
    public ShoppingCart getByUserId(int userId) {
        ShoppingCart cart = new ShoppingCart();
        String sql = "SELECT * FROM shopping_cart WHERE user_id = ?";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet row = statement.executeQuery();
            while (row.next()) {
                int productId = row.getInt("product_id");
                int quantity = row.getInt("quantity");
                // Get the product details
                Product product = productDao.getById(productId);
                if (product != null) {
                    ShoppingCartItem item = new ShoppingCartItem();
                    item.setProduct(product);
                    item.setQuantity(quantity);
                    cart.add(item);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cart;
    }

    // Method to query the database ad add items to the shopping cart table
    @Override
    public void addItemToCart(int userId, int productId, int quantity) {
        String sql = "INSERT INTO shopping_cart (user_id, product_id, quantity) VALUES (?, ?, ?)";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            statement.setInt(3, quantity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to query the database and update an existing item in the cart
    @Override
    public void updateItemInCart(int userId, int productId, int quantity) {
        String sql = "UPDATE shopping_cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setInt(2, userId);
            statement.setInt(3, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to query the database and delete a shopping cart for a user
    @Override
    public void clearCart(int userId) {
        String sql = "DELETE FROM shopping_cart WHERE user_id = ?";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}