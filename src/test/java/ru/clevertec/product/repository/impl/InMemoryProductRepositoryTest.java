package ru.clevertec.product.repository.impl;

import org.junit.jupiter.api.Test;
import ru.clevertec.product.entity.Product;
import ru.clevertec.product.repository.ProductRepository;
import ru.clevertec.product.testdatautil.ProductTestData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.time.Month.OCTOBER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.clevertec.product.testdatautil.TestConstants.TEST_UUID_1;
import static ru.clevertec.product.testdatautil.TestConstants.TEST_UUID_4;

class InMemoryProductRepositoryTest {

    private final ProductRepository productRepository = new InMemoryProductRepository();

    @Test
    void checkFindByIdShouldReturnProduct() {
        // given
        Product expectedProduct = ProductTestData.builder()
                .withUuid(TEST_UUID_1)
                .withName("яблоко")
                .withDescription("грушевидное")
                .withPrice(BigDecimal.valueOf(2.02))
                .withCreated(LocalDateTime.of(2023, OCTOBER, 30, 12, 0, 0))
                .build().buildProduct();

        // when
        Product actualProduct = productRepository.findById(TEST_UUID_1).get();

        // then
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void checkFindByIdShouldReturnOptionalEmpty() {
        // given, when
        Optional<Product> actualProduct = productRepository.findById(TEST_UUID_4);

        // then
        assertEquals(Optional.empty(), actualProduct);
    }

    @Test
    void checkFindAllShouldReturnAllProducts() {
        // given
        int expectedListSize = 5;

        // when
        int actualListSize = productRepository.findAll().size();

        // then
        assertEquals(expectedListSize, actualListSize);
    }

    @Test
    void checkSaveShouldPutProductInInMemoryMap() {
        // given
        int expectedListSize = 6;
        Product productToSave = ProductTestData.builder()
                .withUuid(null)
                .build().buildProduct();

        // when
        productRepository.save(productToSave);

        List<Product> actualList = productRepository.findAll();
        int actualListSize = actualList.size();
        boolean isContains = actualList.contains(productToSave);

        // then
        assertAll(
                () -> assertEquals(expectedListSize, actualListSize),
                () -> assertTrue(isContains)
        );
    }

    @Test
    void checkSaveShouldThrowIllegalArgumentException() {
        // given
        Product firstProductToSave = null;
        Product secondProductToSave = ProductTestData.builder()
                .withName("not-valid-название-продукта")
                .withDescription("not-valid-описание-продукта")
                .withPrice(BigDecimal.valueOf(-1.0))
                .build().buildProduct();

        // when, then
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> productRepository.save(firstProductToSave)),
                () -> assertThrows(IllegalArgumentException.class, () -> productRepository.save(secondProductToSave))
        );
    }

    @Test
    void checkDeleteShouldRemoveProductFromInMemoryMap() {
        // given
        int expectedListSize = 4;
        UUID uuidToDelete = productRepository.findAll().get(1).getUuid();

        // when
        productRepository.delete(uuidToDelete);

        List<Product> actualList = productRepository.findAll();
        int actualListSize = actualList.size();
        boolean isContains = actualList.stream().anyMatch(product -> uuidToDelete.equals(product.getUuid()));

        // then
        assertAll(
                () -> assertEquals(expectedListSize, actualListSize),
                () -> assertFalse(isContains)
        );
    }
}
