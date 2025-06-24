package org.yearup.data;

import org.yearup.models.ShoppingCart;

// Data Access Object (DAO) interface for managing shopping cart data
public interface ShoppingCartDao {
    ShoppingCart getByUserId(int userId);
    void addItemToCart(int userId, int productId, int quantity);
    void updateItemInCart(int userId, int productId, int quantity);
    void clearCart(int userId);
    boolean itemExists(int userId, int productId);
    int getItemQuantity(int userId, int productId);
}