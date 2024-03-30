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

    private final ProductMapper productMapper = new ProductMapperImpl();

    @Test
    void checkToProductShouldReturnCorrectProduct() {
        // given
        ProductDto mappingProductDto = ProductTestData.builder()
                .build().buildProductDto();

        // when
        Product actualProduct = productMapper.toProduct(mappingProductDto);

        // then
        assertThat(actualProduct)
                .hasFieldOrPropertyWithValue(Product.Fields.uuid, isNull())
                .hasFieldOrPropertyWithValue(Product.Fields.name, mappingProductDto.name())
                .hasFieldOrPropertyWithValue(Product.Fields.description, mappingProductDto.description())
                .hasFieldOrPropertyWithValue(Product.Fields.price, mappingProductDto.price())
                .extracting(Product::getCreated).isNotNull();
    }

    @Test
    void checkToInfoProductDtoShouldReturnCorrectInfoProductDto() {
        // given
        Product mappingProduct = ProductTestData.builder()
                .build().buildProduct();

        // when
        InfoProductDto actualInfoProductDto = productMapper.toInfoProductDto(mappingProduct);

        // then
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
        Product actualUpdatedProduct = productMapper.merge(productToMerge, productDtoToMerge);

        // then
        assertThat(actualUpdatedProduct)
                .hasFieldOrPropertyWithValue(Product.Fields.uuid, productToMerge.getUuid())
                .hasFieldOrPropertyWithValue(Product.Fields.name, productDtoToMerge.name())
                .hasFieldOrPropertyWithValue(Product.Fields.description, productDtoToMerge.description())
                .hasFieldOrPropertyWithValue(Product.Fields.price, productDtoToMerge.price())
                .hasFieldOrPropertyWithValue(Product.Fields.created, productToMerge.getCreated());
    }
}
