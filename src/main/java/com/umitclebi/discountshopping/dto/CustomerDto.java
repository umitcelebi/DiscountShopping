package com.umitclebi.discountshopping.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CustomerDto extends ItemDto{

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    private String userName;

    @Size(min = 10,max = 11)
    private String phoneNumber;

    private boolean affiliate;

    private StoreCardDto card;
}
