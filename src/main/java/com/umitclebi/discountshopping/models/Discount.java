package com.umitclebi.discountshopping.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umitclebi.discountshopping.enums.DiscountType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Discount extends Item{
    private DiscountType discountType;
    private BigDecimal totalDiscount;

    @JsonIgnore
    @OneToMany(mappedBy = "discount")
    private List<Cart> carts;
}
