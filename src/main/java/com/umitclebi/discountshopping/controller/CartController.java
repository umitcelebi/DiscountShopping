package com.umitclebi.discountshopping.controller;

import com.umitclebi.discountshopping.models.Cart;
import com.umitclebi.discountshopping.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/cart")
@RestController
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable("cartId") UUID cartId){
        Cart cart=cartService.getCartById(cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> saveCart(@RequestBody Cart cart){
        Cart savedCart=cartService.saveCart(cart);
        return new ResponseEntity<>(savedCart,HttpStatus.CREATED);
    }
}
