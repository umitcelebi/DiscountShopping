package com.umitclebi.discountshopping.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umitclebi.discountshopping.enums.StoreCardEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class StoreCard extends Item{
    private StoreCardEnum cardType;
    private String cartNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "card")
    private List<Customer> customers;
}
