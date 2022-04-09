package com.umitclebi.discountshopping.dto;

import com.umitclebi.discountshopping.enums.StoreCardEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StoreCardDto extends ItemDto{

    @NotNull
    private StoreCardEnum cardType;

    @Size(min = 12,max = 12)
    private String cartNumber;
}
