package ru.clevertec.product.data;

import java.math.BigDecimal;

public record ProductDto(

        String name,
        String description,
        BigDecimal price) {
}
