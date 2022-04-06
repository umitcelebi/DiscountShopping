package com.umitclebi.discountshopping.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @OneToOne(mappedBy = "customer")
    private Cart cart;
}
