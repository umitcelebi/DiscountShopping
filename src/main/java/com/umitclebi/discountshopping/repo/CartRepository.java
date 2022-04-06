package com.umitclebi.discountshopping.repo;

import com.umitclebi.discountshopping.models.Cart;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CartRepository extends PagingAndSortingRepository<Cart, UUID> {
}
