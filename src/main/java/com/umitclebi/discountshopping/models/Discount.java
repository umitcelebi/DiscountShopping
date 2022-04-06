package com.umitclebi.discountshopping.models;

import com.umitclebi.discountshopping.enums.DiscountType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Discount extends Item{
    private DiscountType discountType;
    private BigDecimal totalDiscount;

    @OneToMany(mappedBy = "discount")
    private List<Cart> carts;
}
