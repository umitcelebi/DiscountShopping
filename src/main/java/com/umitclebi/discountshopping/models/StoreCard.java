package com.umitclebi.discountshopping.models;

import com.umitclebi.discountshopping.enums.StoreCardEnum;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class StoreCard extends Item{
    private StoreCardEnum cardType;
    private String cartNumber;

    @OneToMany(mappedBy = "card")
    private List<Customer> customers;
}
