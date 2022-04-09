package com.umitclebi.discountshopping.dto;

import com.umitclebi.discountshopping.enums.PaymentType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CartDto extends ItemDto {

    @NotBlank
    private String deliveryAddress;

    @DecimalMin(value = "0.0")
    private BigDecimal basePrice;

    @DecimalMin(value = "0.0")
    private BigDecimal totalPrice;

    @DecimalMin(value = "0.0")
    private BigDecimal gain;

    @NotNull
    private PaymentType paymentType;

    private DiscountDto discount;

    private List<ProductDto> entries;

    @NotNull
    private CustomerDto customer;
}
