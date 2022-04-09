package com.umitclebi.discountshopping.dto;

import com.umitclebi.discountshopping.enums.ProductTypeEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductDto extends ItemDto{

    @NotNull
    private ProductTypeEnum ProductType;

    @NotBlank
    private String productCode;

    @NotBlank
    private String name;

    @NotBlank
    private String brand;

    @DecimalMin(value = "0.0",inclusive = false)
    private BigDecimal price;

    @PositiveOrZero
    private int Stock;
}
