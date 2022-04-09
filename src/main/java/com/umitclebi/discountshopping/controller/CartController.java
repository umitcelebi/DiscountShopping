package com.umitclebi.discountshopping.controller;

import com.umitclebi.discountshopping.dto.CartDto;
import com.umitclebi.discountshopping.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<CartDto> getCart(@PathVariable("cartId") UUID cartId){
        CartDto cart=cartService.getCartById(cartId);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CartDto> saveCart(@RequestBody @Validated CartDto cartDto){
        cartDto=cartService.calculateTotalPrice(cartDto);
        cartDto=cartService.saveCart(cartDto);
        return new ResponseEntity<>(cartDto,HttpStatus.CREATED);
    }
}
