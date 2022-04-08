
CREATE TABLE IF NOT EXISTS `store_card`
(
    `id`            binary(16) NOT NULL PRIMARY KEY,
    `creation_time` datetime     DEFAULT NULL,
    `modified_time` datetime     DEFAULT NULL,
    `card_type`     varchar(255) DEFAULT NULL,
    `cart_number`   varchar(255) DEFAULT NULL
);



CREATE TABLE IF NOT EXISTS `customer`
(
    `id`            binary(16) NOT NULL PRIMARY KEY,
    `creation_time` datetime     DEFAULT NULL,
    `modified_time` datetime     DEFAULT NULL,
    `affiliate`     bit(1) NOT NULL,
    `last_name`     varchar(255) DEFAULT NULL,
    `name`          varchar(255) DEFAULT NULL,
    `phone_number`  varchar(255) DEFAULT NULL,
    `user_name`     varchar(255) DEFAULT NULL,
    `store_card_id` binary(16) DEFAULT NULL,
    foreign key (store_card_id) references store_card(id)
);



CREATE TABLE IF NOT EXISTS `discount`
(
    `id`             binary(16) NOT NULL PRIMARY KEY,
    `creation_time`  datetime       DEFAULT NULL,
    `modified_time`  datetime       DEFAULT NULL,
    `discount_type`  int            DEFAULT NULL,
    `total_discount` decimal(19, 2) DEFAULT NULL
);



CREATE TABLE IF NOT EXISTS `product`
(
    `id`            binary(16) NOT NULL PRIMARY KEY,
    `creation_time` datetime       DEFAULT NULL,
    `modified_time` datetime       DEFAULT NULL,
    `product_type`  varchar(255)   DEFAULT NULL,
    `stock`         int NOT NULL,
    `brand`         varchar(255)   DEFAULT NULL,
    `name`          varchar(255)   DEFAULT NULL,
    `price`         decimal(19, 2) DEFAULT NULL,
    `product_code`  varchar(255)   DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `cart`
(
    `id`               binary(16) NOT NULL PRIMARY KEY,
    `creation_time`    datetime       DEFAULT NULL,
    `modified_time`    datetime       DEFAULT NULL,
    `base_price`       decimal(19, 2) DEFAULT NULL,
    `delivery_address` varchar(255)   DEFAULT NULL,
    `gain`             decimal(19, 2) DEFAULT NULL,
    `payment_type`     int            DEFAULT NULL,
    `total_price`      decimal(19, 2) DEFAULT NULL,
    `customer_id`      binary(16) DEFAULT NULL,
    `discount_id`      binary(16) DEFAULT NULL,
    foreign key (customer_id) references customer(id),
    foreign key (discount_id) references discount(id)
);


CREATE TABLE IF NOT EXISTS `product_cart`
(
    `product_id` binary(16) NOT NULL,
    `cart_id`    binary(16) NOT NULL,
    foreign key (product_id) references product(id),
    foreign key (cart_id) references cart(id)
);



CREATE TABLE IF NOT EXISTS `cart_entries`
(
    `cart_id`    binary(16) NOT NULL,
    `entries_id` binary(16) NOT NULL,
    foreign key (cart_id) references cart(id),
    foreign key (entries_id) references product(id)
);















