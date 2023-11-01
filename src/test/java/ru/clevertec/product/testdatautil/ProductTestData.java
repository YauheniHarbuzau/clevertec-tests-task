package ru.clevertec.product.testdatautil;

import lombok.Builder;
import lombok.Data;
import ru.clevertec.product.data.InfoProductDto;
import ru.clevertec.product.data.ProductDto;
import ru.clevertec.product.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.Month.OCTOBER;
import static ru.clevertec.product.testdatautil.TestConstants.TEST_UUID_1;

@Data
@Builder(setterPrefix = "with")
public class ProductTestData {

    @Builder.Default
    private UUID uuid = TEST_UUID_1;
    @Builder.Default
    private String name = "арбуз";
    @Builder.Default
    private String description = "тестируемый";
    @Builder.Default
    private BigDecimal price = BigDecimal.valueOf(10.00);
    @Builder.Default
    private LocalDateTime created = LocalDateTime.of(2023, OCTOBER, 30, 12, 0, 0);

    public Product buildProduct() {
        return new Product(uuid, name, description, price, created);
    }

    public ProductDto buildProductDto() {
        return new ProductDto(name, description, price);
    }

    public InfoProductDto buildInfoProductDto() {
        return new InfoProductDto(uuid, name, description, price);
    }
}
