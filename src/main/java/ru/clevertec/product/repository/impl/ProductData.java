package ru.clevertec.product.repository.impl;

import lombok.Getter;
import lombok.Setter;
import ru.clevertec.product.entity.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static ru.clevertec.product.constants.Constants.CREATION_DATE_TIME;

public class ProductData {

    private final Product product1 = Product.builder()
            .uuid(UUID.fromString("cb7f2552-a60d-4831-a5f2-ab1cb1262def"))
            .name("яблоко")
            .description("грушевидное")
            .price(BigDecimal.valueOf(2.02))
            .created(CREATION_DATE_TIME)
            .build();

    private final Product product2 = Product.builder()
            .uuid(UUID.fromString("165830f5-6fa0-483d-8487-49f718be5630"))
            .name("арбуз")
            .description("квадратный")
            .price(BigDecimal.valueOf(5.10))
            .created(CREATION_DATE_TIME)
            .build();

    private final Product product3 = Product.builder()
            .uuid(UUID.fromString("8ad6415a-7175-46fd-8ff3-7f9ee459578c"))
            .name("газировка")
            .description("пузырчатая")
            .price(BigDecimal.valueOf(3.15))
            .created(CREATION_DATE_TIME)
            .build();

    private final Product product4 = Product.builder()
            .uuid(UUID.fromString("0fcaec59-033c-4039-8b90-1e9fbbbe2033"))
            .name("водка")
            .description("безалкогольная")
            .price(BigDecimal.valueOf(3.10))
            .created(CREATION_DATE_TIME)
            .build();

    private final Product product5 = Product.builder()
            .uuid(UUID.fromString("f154378c-721d-499b-b760-333797ce3854"))
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
