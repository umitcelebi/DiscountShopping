package com.umitclebi.discountshopping.models;

import com.umitclebi.discountshopping.enums.PaymentType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Cart extends Item{
    private String deliveryAddress;
    private BigDecimal basePrice;
    private BigDecimal totalPrice;
    private BigDecimal gain;
    private PaymentType paymentType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Product> entries;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;
}
