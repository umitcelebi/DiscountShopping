package com.umitclebi.discountshopping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umitclebi.discountshopping.enums.PaymentType;
import com.umitclebi.discountshopping.enums.ProductTypeEnum;
import com.umitclebi.discountshopping.enums.StoreCardEnum;
import com.umitclebi.discountshopping.models.*;
import com.umitclebi.discountshopping.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CartController.class)
class CartControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Autowired
    ObjectMapper objectMapper;

    Cart cart;
    Customer customer;
    List<Product> products;
    StoreCard storeCard;

    @BeforeEach
    public void setUp() {
        storeCard=StoreCard.builder()
                .cardType(StoreCardEnum.GOLD)
                .cartNumber("45645354354666")
                .build();
        customer=Customer.builder()
                .name("Ümit")
                .lastName("Çelebi")
                .phoneNumber("05555555555")
                .affiliate(false)
                .card(storeCard)
                .build();
        products= Arrays.asList(Product.builder()
                        .ProductType(ProductTypeEnum.TV)
                        .productCode("SM2342")
                        .name("Samsung 42\" TV")
                        .brand("SAMSUNG")
                        .price(new BigDecimal("13450"))
                        .build(),
                Product.builder()
                        .ProductType(ProductTypeEnum.CAMERA)
                        .productCode("NK384937")
                        .name("Nikon D100")
                        .brand("NIKON")
                        .price(new BigDecimal("6600"))
                        .build());
        cart=Cart.builder()
                .deliveryAddress("Istanbul/Turkey")
                .paymentType(PaymentType.CARD)
                .entries(products)
                .customer(customer)
                .build();
        cart.setId(UUID.fromString("6aa6148b-67ee-4364-bb70-8c1727360710"));
    }

    @Test
    void getCart() throws Exception {

        mockMvc.perform(get("/cart/" + cart.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(cart.getId())));
    }

    @Test
    void saveCart() throws Exception {
        storeCard = StoreCard.builder()
                .cardType(StoreCardEnum.SILVER)
                .cartNumber("255623232")
                .build();
        customer = Customer.builder()
                .name("Muhammed")
                .lastName("Yılmaz")
                .phoneNumber("05444444444")
                .affiliate(false)
                .card(storeCard)
                .build();
        cart = Cart.builder()
                .deliveryAddress("Samsun/TURKEY")
                .entries(products)
                .customer(customer)
                .paymentType(PaymentType.BANK_TRANSFER)
                .build();
        cartService.calculateTotalPrice(cart);

        String cartDtoJson = objectMapper.writeValueAsString(cart);

        mockMvc.perform(post("/cart/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cartDtoJson))
                .andExpect(status().isCreated());
    }
}