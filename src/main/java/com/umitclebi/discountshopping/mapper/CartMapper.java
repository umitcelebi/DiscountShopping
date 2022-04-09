package com.umitclebi.discountshopping.mapper;

import com.umitclebi.discountshopping.dto.CartDto;
import com.umitclebi.discountshopping.models.Cart;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface CartMapper {
    Cart cartDtoToCart(CartDto cartDto);
    CartDto cartToCartDto(Cart cart);
}
