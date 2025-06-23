package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao {

    public MySqlCategoryDao(DataSource dataSource) {
        super(dataSource);
    }

    ///
    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM Categories";
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet row = statement.executeQuery()) {
            while (row.next()) {
                Category category = mapRow(row);
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    ///
    @Override
    public Category getById(int categoryId) {
        String sql = "SELECT * FROM categories WHERE category_id = ?";
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            ResultSet row = statement.executeQuery();
            if (row.next()) {
                return mapRow(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    ///
    @Override
    public Category create(Category category) {
        String sql = "INSERT INTO categories(name, description) VALUES (?, ?);";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                // In this case, another try with resources is needed to handle the get generated keys ResultSet
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int categoryId = generatedKeys.getInt(1);
                        return getById(categoryId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    ///
    @Override
    public void update(int categoryId, Category category) {
        // update category
    }

    ///
    @Override
    public void delete(int categoryId) {
        // delete category
    }

    private Category mapRow(ResultSet row) throws SQLException {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category() {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};
        return category;
    }
}

//@Override
//public Product create(Product product) {
//    String sql = "INSERT INTO products(name, price, category_id, description, color, image_url, stock, featured) " +
//            " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
//
//    try (Connection connection = getConnection()) {
//        PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//        statement.setString(1, product.getName());
//        statement.setBigDecimal(2, product.getPrice());
//        statement.setInt(3, product.getCategoryId());
//        statement.setString(4, product.getDescription());
//        statement.setString(5, product.getColor());
//        statement.setString(6, product.getImageUrl());
//        statement.setInt(7, product.getStock());
//        statement.setBoolean(8, product.isFeatured());
//
//        int rowsAffected = statement.executeUpdate();
//
//        if (rowsAffected > 0) {
//            // Retrieve the generated keys
//            ResultSet generatedKeys = statement.getGeneratedKeys();
//
//            if (generatedKeys.next()) {
//                // Retrieve the auto-incremented ID
//                int orderId = generatedKeys.getInt(1);
//
//                // get the newly inserted category
//                return getById(orderId);
//            }
//        }
//    } catch (SQLException e) {
//        throw new RuntimeException(e);
//    }
//    return null;
//}
//
//@Override
//public void update(int productId, Product product) {
//    String sql = "UPDATE products" +
//            " SET name = ? " +
//            "   , price = ? " +
//            "   , category_id = ? " +
//            "   , description = ? " +
//            "   , color = ? " +
//            "   , image_url = ? " +
//            "   , stock = ? " +
//            "   , featured = ? " +
//            " WHERE product_id = ?;";
//
//    try (Connection connection = getConnection()) {
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1, product.getName());
//        statement.setBigDecimal(2, product.getPrice());
//        statement.setInt(3, product.getCategoryId());
//        statement.setString(4, product.getDescription());
//        statement.setString(5, product.getColor());
//        statement.setString(6, product.getImageUrl());
//        statement.setInt(7, product.getStock());
//        statement.setBoolean(8, product.isFeatured());
//        statement.setInt(9, productId);
//
//        statement.executeUpdate();
//    } catch (SQLException e) {
//        throw new RuntimeException(e);
//    }
//}
//
//@Override
//public void delete(int productId) {
//    String sql = "DELETE FROM products " +
//            " WHERE product_id = ?;";
//
//    try (Connection connection = getConnection()) {
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setInt(1, productId);
//
//        statement.executeUpdate();
//    } catch (SQLException e) {
//        throw new RuntimeException(e);
//    }
//}
//
//protected static Product mapRow(ResultSet row) throws SQLException {
//    int productId = row.getInt("product_id");
//    String name = row.getString("name");
//    BigDecimal price = row.getBigDecimal("price");
//    int categoryId = row.getInt("category_id");
//    String description = row.getString("description");
//    String color = row.getString("color");
//    int stock = row.getInt("stock");
//    boolean isFeatured = row.getBoolean("featured");
//    String imageUrl = row.getString("image_url");
//
//    return new Product(productId, name, price, categoryId, description, color, stock, isFeatured, imageUrl);
//}