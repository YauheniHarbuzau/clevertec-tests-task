package ru.clevertec.product.mapper.impl;

import org.junit.jupiter.api.Test;
import ru.clevertec.product.data.InfoProductDto;
import ru.clevertec.product.data.ProductDto;
import ru.clevertec.product.entity.Product;
import ru.clevertec.product.mapper.ProductMapper;
import ru.clevertec.product.mapper.ProductMapperImpl;
import ru.clevertec.product.testdatautil.ProductTestData;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.isNull;

class ProductMapperImplTest {

    private ProductMapper mapper = new ProductMapperImpl();

    @Test
    void checkToProductShouldReturnCorrectProduct() {
        // given
        ProductDto mappingProductDto = ProductTestData.builder()
                .build().buildProductDto();

        // when
        Product actualProduct = mapper.toProduct(mappingProductDto);

        // than
        assertThat(actualProduct)
                .hasFieldOrPropertyWithValue(Product.Fields.uuid, isNull())
                .hasFieldOrPropertyWithValue(Product.Fields.name, mappingProductDto.name())
                .hasFieldOrPropertyWithValue(Product.Fields.description, mappingProductDto.description())
                .hasFieldOrPropertyWithValue(Product.Fields.price, mappingProductDto.price());
        assertThat(actualProduct.getCreated()).isNotNull();
    }

    @Test
    void checkToInfoProductDtoShouldReturnCorrectInfoProductDto() {
        // given
        Product mappingProduct = ProductTestData.builder()
                .build().buildProduct();

        // when
        InfoProductDto actualInfoProductDto = mapper.toInfoProductDto(mappingProduct);

        // than
        assertThat(actualInfoProductDto)
                .hasFieldOrPropertyWithValue("uuid", mappingProduct.getUuid())
                .hasFieldOrPropertyWithValue("name", mappingProduct.getName())
                .hasFieldOrPropertyWithValue("description", mappingProduct.getDescription())
                .hasFieldOrPropertyWithValue("price", mappingProduct.getPrice());
    }

    @Test
    void checkMergeShouldReturnCorrectProduct() {
        // given
        Product productToMerge = ProductTestData.builder()
                .build().buildProduct();
        ProductDto productDtoToMerge = ProductTestData.builder()
                .withName("арбузик")
                .withDescription("изменённый")
                .withPrice(BigDecimal.valueOf(20.00))
                .build().buildProductDto();

        // when
        Product actualUpdatedProduct = mapper.merge(productToMerge, productDtoToMerge);

        // than
        assertThat(actualUpdatedProduct)
                .hasFieldOrPropertyWithValue("uuid", productToMerge.getUuid())
                .hasFieldOrPropertyWithValue("name", productDtoToMerge.name())
                .hasFieldOrPropertyWithValue("description", productDtoToMerge.description())
                .hasFieldOrPropertyWithValue("price", productDtoToMerge.price())
                .hasFieldOrPropertyWithValue("created", productToMerge.getCreated());
    }
}
