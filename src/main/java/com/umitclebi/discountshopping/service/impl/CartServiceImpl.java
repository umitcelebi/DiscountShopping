package com.umitclebi.discountshopping.service.impl;

import com.umitclebi.discountshopping.dto.CartDto;
import com.umitclebi.discountshopping.dto.DiscountDto;
import com.umitclebi.discountshopping.dto.ProductDto;
import com.umitclebi.discountshopping.enums.DiscountType;
import com.umitclebi.discountshopping.enums.ProductTypeEnum;
import com.umitclebi.discountshopping.enums.StoreCardEnum;
import com.umitclebi.discountshopping.mapper.CartMapper;
import com.umitclebi.discountshopping.models.*;
import com.umitclebi.discountshopping.repo.CartRepository;
import com.umitclebi.discountshopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {


    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartMapper cartMapper;

    @Override
    public CartDto getCartById(UUID uuid) {

        return cartMapper.cartToCartDto(cartRepository.findById(uuid).orElse(null));
    }

    @Override
    public CartDto saveCart(CartDto cartDto) {
        Cart cart=cartMapper.cartDtoToCart(cartDto);
        return cartMapper.cartToCartDto(cartRepository.save(cart));
    }

    @Override
    public CartDto calculateTotalPrice(CartDto cartDto) {
        cartDto.setBasePrice(calculateBasePrice(cartDto).getBasePrice());
        cartDto.setDiscount(Objects.nonNull(calculateDiscount(cartDto).getDiscount())?calculateDiscount(cartDto).getDiscount():null);
        if(Objects.nonNull(cartDto.getDiscount())){
            cartDto.setTotalPrice(cartDto.getBasePrice().subtract(cartDto.getDiscount().getTotalDiscount()));
        }
        return cartDto;
    }

    @Override
    public CartDto calculateDiscount(CartDto cartDto) {
        BigDecimal result = BigDecimal.ZERO;
        DiscountDto discount=DiscountDto.builder().totalDiscount(result).build();
        if (Objects.nonNull(cartDto.getEntries()) && cartDto.getEntries().stream().anyMatch(product -> product.getProductType().equals(ProductTypeEnum.PHONE))) return cartDto;

        if (Objects.nonNull(cartDto.getCustomer().getCard()) && cartDto.getCustomer().getCard().getCardType().equals(StoreCardEnum.GOLD)){
            result=cartDto.getBasePrice().multiply(new BigDecimal("0.3"));
            discount.setDiscountType(DiscountType.STORE_CARD);
        }else if (Objects.nonNull(cartDto.getCustomer().getCard()) && cartDto.getCustomer().getCard().getCardType().equals(StoreCardEnum.SILVER)){
            result=cartDto.getBasePrice().multiply(new BigDecimal("0.2"));
            discount.setDiscountType(DiscountType.STORE_CARD);
        }else if(cartDto.getCustomer().isAffiliate()){
            result=cartDto.getBasePrice().multiply(new BigDecimal("0.1"));
            discount.setDiscountType(DiscountType.AFFILIATE);
        }else if(OffsetDateTime.now().plusDays(-365).compareTo(cartDto.getCustomer().getCreationTime())>=0){
            result=cartDto.getBasePrice().multiply(new BigDecimal("0.05"));
            discount.setDiscountType(DiscountType.OLD_USER);
        }else{
            result=new BigDecimal(cartDto.getBasePrice().divide(new BigDecimal(200)).intValue()).multiply(new BigDecimal(5));
            discount.setDiscountType(DiscountType.HIGH_WAGE);
        }
        discount.setTotalDiscount(result);
        cartDto.setDiscount(discount);
        return cartDto;
    }

    @Override
    public CartDto calculateBasePrice(CartDto cartDto) {
        BigDecimal basePrice = BigDecimal.ZERO;
        cartDto.getEntries().removeAll(removeNoStockProduct(cartDto));
        basePrice=Objects.nonNull(cartDto.getEntries()) ?
                cartDto.getEntries().stream().map(ProductDto::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add)
                :basePrice;
        cartDto.setBasePrice(basePrice);
        return cartDto;
    }

    @Override
    public List<ProductDto> removeNoStockProduct(CartDto cartDto) {
        if(Objects.nonNull(cartDto.getEntries())){
            return cartDto.getEntries().stream().filter(p->p.getStock()<=0).collect(Collectors.toList());
        }
        return null;
    }

}


