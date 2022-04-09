package com.umitclebi.discountshopping.dto;

import com.umitclebi.discountshopping.enums.DiscountType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DiscountDto extends ItemDto{

    @NotNull
    private DiscountType discountType;

    @DecimalMin(value = "0.0")
    private BigDecimal totalDiscount;
}
