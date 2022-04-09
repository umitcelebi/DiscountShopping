package com.umitclebi.discountshopping.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Customer extends Item{
    private String name;
    private String lastName;
    private String userName;
    private String phoneNumber;

    private boolean affiliate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="store_card_id")
    private StoreCard card;

    @JsonIgnore
    @OneToOne(mappedBy = "customer")
    private Cart cart;
}
