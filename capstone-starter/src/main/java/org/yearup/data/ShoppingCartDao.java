package org.yearup.data;

import org.yearup.models.ShoppingCart;

// Data Access Object (DAO) interface for managing shopping cart data
public interface ShoppingCartDao {
    ShoppingCart getByUserId(int userId);
    // add additional method signatures here
}