package com.umitclebi.discountshopping.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ItemDto {

    @Null
    private UUID id;

    @Past
    private OffsetDateTime creationTime;

    @Past
    private OffsetDateTime modifiedTime;
}
