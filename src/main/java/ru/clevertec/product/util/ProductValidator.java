package ru.clevertec.product.util;

import ru.clevertec.product.data.ProductDto;
import ru.clevertec.product.entity.Product;

import java.math.BigDecimal;

import static ru.clevertec.product.constants.Constants.PRODUCT_DESCRIPTION_PATTERN;
import static ru.clevertec.product.constants.Constants.PRODUCT_NAME_PATTERN;

public class ProductValidator {

    public static boolean isValid(Product product) {
        return product.getName() != null &&
                product.getName().matches(PRODUCT_NAME_PATTERN) &&
                (product.getDescription() == null ||
                product.getDescription().matches(PRODUCT_DESCRIPTION_PATTERN)) &&
                product.getPrice() != null &&
                product.getPrice().compareTo(BigDecimal.valueOf(0.0)) >= 0 &&
                product.getCreated() != null;
    }

    public static boolean isValid(ProductDto productDto) {
        return productDto.name() != null &&
                productDto.name().matches(PRODUCT_NAME_PATTERN) &&
                (productDto.description() == null ||
                productDto.description().matches(PRODUCT_DESCRIPTION_PATTERN)) &&
                productDto.price() != null &&
                productDto.price().compareTo(BigDecimal.valueOf(0.0)) >= 0;
    }
}
