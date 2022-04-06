package com.umitclebi.discountshopping.models;

import lombok.*;

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
@Builder
@Entity
public class Product extends Item{
    private String ProductType;
    private String productCode;
    private String name;
    private String brand;
    private BigDecimal price;
    private int Stock;

    @ManyToMany
    @JoinTable(name = "product_cart",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "cart_id"))
    private List<Cart> carts;
}
