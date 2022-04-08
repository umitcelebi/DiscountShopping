INSERT INTO `store_card` (`id`, `creation_time`, `modified_time`, `card_type`, `cart_number`) VALUES (RAWTOHEX('8e2972c6'), '2022-04-08 13:59:14', '2022-04-08 13:59:14', 1, '453453455');

INSERT INTO `customer` (`id`, `creation_time`, `modified_time`, `affiliate`, `last_name`, `name`, `phone_number`, `user_name`, `store_card_id`) VALUES (RAWTOHEX('8e2972b7'), '2022-04-08 13:59:14', '2022-04-08 13:59:14', 1, 'Çelebi', 'Ümit', '05555555555', 'ucelebi', RAWTOHEX('8e2972c6'));

INSERT INTO `discount` (`id`, `creation_time`, `modified_time`, `discount_type`, `total_discount`) VALUES (RAWTOHEX('115054e0'), '2022-04-08 13:59:14', '2022-04-08 13:59:14', 1, '9600');

INSERT INTO `product` (`id`, `creation_time`, `modified_time`, `product_type`, `stock`, `brand`, `name`, `price`, `product_code`) VALUES (RAWTOHEX('115054e0'), '2022-04-08 13:59:14', '2022-04-08 13:59:14', 0, '11', 'SAMSUNG', 'Samsung 50 inç QLED', '16000', 'QN90A');

INSERT INTO `cart` (`id`, `creation_time`, `modified_time`,`base_price`, `total_price`, `delivery_address`, `payment_type`, `customer_id`, `discount_id`) VALUES (RAWTOHEX('115056y9'), '2022-04-08 13:59:14', '2022-04-08 13:59:14', '16000','6400','Istanbul/TURKEY', 0, RAWTOHEX('8e2972b7'), RAWTOHEX('115054e0'));

INSERT INTO `cart_entries` (`cart_id`, `entries_id`) VALUES (RAWTOHEX('115056y9'),RAWTOHEX('115054e0'));



