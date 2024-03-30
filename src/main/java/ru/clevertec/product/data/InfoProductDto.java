package ru.clevertec.product.data;

import java.math.BigDecimal;
import java.util.UUID;

public record InfoProductDto(

        UUID uuid,
        String name,
        String description,
        BigDecimal price) {
}
