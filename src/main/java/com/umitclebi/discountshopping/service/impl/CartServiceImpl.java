package com.umitclebi.discountshopping.service.impl;

import com.umitclebi.discountshopping.enums.DiscountType;
import com.umitclebi.discountshopping.models.*;
import com.umitclebi.discountshopping.repo.CartRepository;
import com.umitclebi.discountshopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class CartServiceImpl implements CartService {

    private final String GOLD_CARD="Gold";
    private final String SILVER_CARD="Silver";
    private final String PRODUCT_TYPE_PHONE="Phone";

    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart getCartById(UUID uuid) {
        return cartRepository.findById(uuid).orElse(null);
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart calculateTotalPrice(Cart cart) {
        cart.setBasePrice(calculateBasePrice(cart).getBasePrice());
        cart.setDiscount(calculateDiscount(cart).getDiscount());
        if(Objects.nonNull(cart.getDiscount())){
            cart.setTotalPrice(cart.getBasePrice().subtract(cart.getDiscount().getTotalDiscount()));
        }
        return cart;
    }

    @Override
    public Cart calculateDiscount(Cart cart) {
        BigDecimal result = BigDecimal.ZERO;
        Discount discount=Discount.builder().totalDiscount(result).build();
        if (cart.getEntries().stream().anyMatch(product -> product.getProductType().equals(PRODUCT_TYPE_PHONE))) return cart;

        if (Objects.nonNull(cart.getCustomer().getCard()) && cart.getCustomer().getCard().getCardType().equals(GOLD_CARD)){
            result=cart.getBasePrice().multiply(new BigDecimal("0.3"));
            discount.setDiscountType(DiscountType.STORE_CARD);
        }else if (Objects.nonNull(cart.getCustomer().getCard()) && cart.getCustomer().getCard().getCardType().equals(SILVER_CARD)){
            result=cart.getBasePrice().multiply(new BigDecimal("0.2"));
            discount.setDiscountType(DiscountType.STORE_CARD);
        }else if(cart.getCustomer().isAffiliate()){
            result=cart.getBasePrice().multiply(new BigDecimal("0.1"));
            discount.setDiscountType(DiscountType.AFFILIATE);
        }else if(TimeUnit.MILLISECONDS.toDays(Math.abs(Calendar.getInstance().getTimeInMillis() - cart.getCustomer().getCreationTime().getTime()))>=365){
            result=cart.getBasePrice().multiply(new BigDecimal("0.05"));
            discount.setDiscountType(DiscountType.OLD_USER);
        }else{
            result=new BigDecimal(cart.getBasePrice().divide(new BigDecimal(200)).intValue()).multiply(new BigDecimal(5));
            discount.setDiscountType(DiscountType.HIGH_WAGE);
        }
        discount.setTotalDiscount(result);
        cart.setDiscount(discount);
        return cart;
    }

    @Override
    public Cart calculateBasePrice(Cart cart) {
        BigDecimal basePrice = BigDecimal.ZERO;
        basePrice=Objects.nonNull(cart.getEntries()) ?
                cart.getEntries().stream().map(Product::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add)
                :basePrice;
        cart.setBasePrice(basePrice);
        return cart;
    }

}


