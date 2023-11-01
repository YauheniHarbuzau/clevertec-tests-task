package ru.clevertec.product.repository.impl;

import lombok.Getter;
import lombok.Setter;
import ru.clevertec.product.entity.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static ru.clevertec.product.constants.Constants.CREATION_DATE_TIME;
import static ru.clevertec.product.constants.Constants.UUID_1;
import static ru.clevertec.product.constants.Constants.UUID_2;
import static ru.clevertec.product.constants.Constants.UUID_3;
import static ru.clevertec.product.constants.Constants.UUID_4;
import static ru.clevertec.product.constants.Constants.UUID_5;

public class ProductData {

    private final Product product1 = Product.builder()
            .uuid(UUID_1)
            .name("яблоко")
            .description("грушевидное")
            .price(BigDecimal.valueOf(2.02))
            .created(CREATION_DATE_TIME)
            .build();
    private final Product product2 = Product.builder()
            .uuid(UUID_2)
            .name("арбуз")
            .description("квадратный")
            .price(BigDecimal.valueOf(5.10))
            .created(CREATION_DATE_TIME)
            .build();
    private final Product product3 = Product.builder()
            .uuid(UUID_3)
            .name("газировка")
            .description("пузырчатая")
            .price(BigDecimal.valueOf(3.15))
            .created(CREATION_DATE_TIME)
            .build();
    private final Product product4 = Product.builder()
            .uuid(UUID_4)
            .name("водка")
            .description("безалкогольная")
            .price(BigDecimal.valueOf(3.10))
            .created(CREATION_DATE_TIME)
            .build();
    private final Product product5 = Product.builder()
            .uuid(UUID_5)
            .name("колбаса")
            .description("непонятная")
            .price(BigDecimal.valueOf(4.30))
            .created(CREATION_DATE_TIME)
            .build();

    @Getter
    @Setter
    private Map<UUID, Product> productDataMap = new HashMap<>();

    {
        productDataMap.put(product1.getUuid(), product1);
        productDataMap.put(product2.getUuid(), product2);
        productDataMap.put(product3.getUuid(), product3);
        productDataMap.put(product4.getUuid(), product4);
        productDataMap.put(product5.getUuid(), product5);
    }
}
