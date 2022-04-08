package com.umitclebi.discountshopping.service;

import com.umitclebi.discountshopping.models.Cart;
import com.umitclebi.discountshopping.models.Product;

import java.util.List;
import java.util.UUID;

public interface CartService {
    Cart getCartById(UUID uuid);
    Cart saveCart(Cart cart);
    Cart calculateTotalPrice(Cart cart);
    Cart calculateDiscount(Cart cart);
    Cart calculateBasePrice(Cart cart);
    List<Product> removeNoStockProduct(Cart cart);
}
