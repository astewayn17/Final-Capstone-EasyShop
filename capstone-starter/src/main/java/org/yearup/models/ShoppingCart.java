package org.yearup.models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    // A map of product IDs to their corresponding shopping cart items
    private Map<Integer, ShoppingCartItem> items = new HashMap<>();

    // Returns the current map of cart items
    public Map<Integer, ShoppingCartItem> getItems() { return items; }

    // Replaces the current items in the cart with the given map
    public void setItems(Map<Integer, ShoppingCartItem> items) { this.items = items; }

    // Checks if the cart contains an item with the given product ID
    public boolean contains(int productId) { return items.containsKey(productId); }

    // Adds a new item to the cart or replaces an existing one with the same product ID
    public void add(ShoppingCartItem item) { items.put(item.getProductId(), item); }

    // Retrieves the ShoppingCartItem for the specified product ID
    public ShoppingCartItem get(int productId) { return items.get(productId); }

    // Calculates and returns the total cost of all items in the cart using a stream method
    public BigDecimal getTotal() {
        BigDecimal total = items.values()
                                .stream()
                                .map(i -> i.getLineTotal())
                                .reduce( BigDecimal.ZERO, (lineTotal, subTotal) -> subTotal.add(lineTotal));
        return total;
    }
}