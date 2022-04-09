package com.umitclebi.discountshopping.service;

import com.umitclebi.discountshopping.dto.CartDto;
import com.umitclebi.discountshopping.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface CartService {
    CartDto getCartById(UUID uuid);
    CartDto saveCart(CartDto cart);
    CartDto calculateTotalPrice(CartDto cart);
    CartDto calculateDiscount(CartDto cart);
    CartDto calculateBasePrice(CartDto cart);
    List<ProductDto> removeNoStockProduct(CartDto cart);
}
