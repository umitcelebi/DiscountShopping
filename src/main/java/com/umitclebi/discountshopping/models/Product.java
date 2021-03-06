package com.umitclebi.discountshopping.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umitclebi.discountshopping.enums.ProductTypeEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Product extends Item{
    private ProductTypeEnum ProductType;
    private String productCode;
    private String name;
    private String brand;
    private BigDecimal price;
    private int Stock;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "product_cart",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "cart_id"))
    private List<Cart> carts;
}
