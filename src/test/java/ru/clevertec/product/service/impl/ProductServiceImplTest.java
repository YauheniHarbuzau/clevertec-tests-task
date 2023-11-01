package ru.clevertec.product.service.impl;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.product.data.InfoProductDto;
import ru.clevertec.product.data.ProductDto;
import ru.clevertec.product.entity.Product;
import ru.clevertec.product.exception.ProductNotFoundException;
import ru.clevertec.product.mapper.ProductMapper;
import ru.clevertec.product.repository.ProductRepository;
import ru.clevertec.product.repository.impl.InMemoryProductRepository;
import ru.clevertec.product.testdatautil.ProductTestData;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.clevertec.product.testdatautil.TestConstants.TEST_UUID_1;
import static ru.clevertec.product.testdatautil.TestConstants.TEST_UUID_2;
import static ru.clevertec.product.testdatautil.TestConstants.TEST_UUID_3;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository = new InMemoryProductRepository();
    @Mock
    private ProductMapper mapper = new ProductMapperImpl();
    @InjectMocks
    private ProductServiceImpl productService;
    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @Nested
    class GetTest {
        @Test
        void checkGetShouldCalledRepositoryMethod() {
            // given
            Product product = ProductTestData.builder()
                    .build().buildProduct();

            doReturn(Optional.of(product))
                    .when(productRepository).findById(TEST_UUID_1);

            // when
            productService.get(TEST_UUID_1);

            // than
            verify(productRepository).findById(TEST_UUID_1);
        }

        @Test
        void checkGetShouldReturnProduct() {
            // given
            Product expectedProduct = ProductTestData.builder()
                    .build().buildProduct();
            InfoProductDto expectedProductDto = ProductTestData.builder()
                    .build().buildInfoProductDto();

            doReturn(Optional.of(expectedProduct))
                    .when(productRepository).findById(TEST_UUID_1);
            doReturn(expectedProductDto)
                    .when(mapper).toInfoProductDto(expectedProduct);

            // when
            InfoProductDto actualProductDto = productService.get(TEST_UUID_1);

            // than
            assertEquals(expectedProductDto, actualProductDto);
        }

        @Test
        void checkGetShouldThrowProductNotFoundException() {
            // given
            UUID uuidFound = TEST_UUID_1;
            UUID uuidNotFound = TEST_UUID_2;

            Product product = ProductTestData.builder()
                    .withUuid(TEST_UUID_1)
                    .build().buildProduct();
            InfoProductDto productDto = ProductTestData.builder()
                    .withUuid(TEST_UUID_1)
                    .build().buildInfoProductDto();

            doReturn(Optional.of(product))
                    .when(productRepository).findById(uuidFound);
            doReturn(productDto)
                    .when(mapper).toInfoProductDto(product);

            // when, than
            assertAll(
                    () -> assertDoesNotThrow(() -> productService.get(uuidFound)),
                    () -> assertThrows(ProductNotFoundException.class, () -> productService.get(uuidNotFound))
            );
        }

        @Test
        void checkGetShouldThrowNullPointerException() {
            // given
            UUID uuid = TEST_UUID_1;

            doReturn(null)
                    .when(productRepository).findById(uuid);

            // when, than
            assertThrows(NullPointerException.class, () -> productService.get(uuid));
        }
    }

    @Nested
    class GetAllTest {
        @Test
        void checkGetAllShouldCalledRepositoryMethod() {
            // given, when
            productService.getAll();

            // than
            verify(productRepository).findAll();
        }

        @Test
        void checkGetAllShouldReturnAllProducts() {
            // given
            Product product1 = ProductTestData.builder()
                    .withUuid(TEST_UUID_1)
                    .build().buildProduct();
            Product product2 = ProductTestData.builder()
                    .withUuid(TEST_UUID_2)
                    .build().buildProduct();
            InfoProductDto productDto1 = ProductTestData.builder()
                    .withUuid(TEST_UUID_1)
                    .build().buildInfoProductDto();
            InfoProductDto productDto2 = ProductTestData.builder()
                    .withUuid(TEST_UUID_2)
                    .build().buildInfoProductDto();

            List<InfoProductDto> expectedProductDtoList = List.of(productDto1, productDto2);

            doReturn(List.of(product1, product2))
                    .when(productRepository).findAll();
            doReturn(productDto1)
                    .when(mapper).toInfoProductDto(product1);
            doReturn(productDto2)
                    .when(mapper).toInfoProductDto(product2);

            // when
            List<InfoProductDto> actualProductDtoList = productService.getAll();

            // than
            assertAll(
                    () -> assertEquals(expectedProductDtoList, actualProductDtoList),
                    () -> assertEquals(2, actualProductDtoList.size())
            );
        }

        @Test
        void checkGetAllShouldReturnEmptyList() {
            // given
            List<InfoProductDto> expectedProductDtoList = Collections.emptyList();

            doReturn(Collections.emptyList()).when(productRepository).findAll();

            // when
            List<InfoProductDto> actualProductDtoList = productService.getAll();

            // than
            assertEquals(expectedProductDtoList, actualProductDtoList);
        }
    }

    @Nested
    class CreateTest {
        @Test
        void checkCreateShouldCalledRepositoryMethod() {
            // given
            Product product = ProductTestData.builder()
                    .build().buildProduct();
            ProductDto productDto = ProductTestData.builder()
                    .build().buildProductDto();

            doReturn(product)
                    .when(mapper).toProduct(productDto);
            doReturn(product)
                    .when(productRepository).save(product);

            // when
            productService.create(productDto);

            // than
            verify(productRepository).save(product);
        }

        @Test
        void checkCreateShouldSavedWithNullProductUuid() {
            // given
            Product product = ProductTestData.builder()
                    .withUuid(null)
                    .build().buildProduct();
            ProductDto productDto = ProductTestData.builder()
                    .build().buildProductDto();

            doReturn(product)
                    .when(mapper).toProduct(productDto);
            doReturn(product)
                    .when(productRepository).save(product);

            // when
            productService.create(productDto);

            // than
            verify(productRepository).save(productCaptor.capture());
            assertNull(productCaptor.getValue().getUuid());
        }
    }

    @Nested
    class UpdateTest {
        @Test
        void checkUpdateShouldSavedCorrectProduct() {
            // given
            UUID actualUuid = TEST_UUID_1;

            Product actualProduct = ProductTestData.builder()
                    .withUuid(actualUuid)
                    .withName("пришёл")
                    .withDescription("изменяться")
                    .build().buildProduct();
            ProductDto actualProductDto = ProductTestData.builder()
                    .withName("вышел")
                    .withDescription("изменённым")
                    .build().buildProductDto();
            Product expectedProduct = ProductTestData.builder()
                    .withUuid(actualUuid)
                    .withName("вышел")
                    .withDescription("изменённым")
                    .build().buildProduct();

            doReturn(Optional.of(actualProduct))
                    .when(productRepository).findById(actualUuid);
            doReturn(expectedProduct)
                    .when(mapper).merge(actualProduct, actualProductDto);
            doReturn(expectedProduct)
                    .when(productRepository).save(expectedProduct);

            // when
            productService.update(actualUuid, actualProductDto);

            // than
            verify(productRepository).save(productCaptor.capture());
            assertAll(
                    () -> assertEquals(expectedProduct.getUuid(), productCaptor.getValue().getUuid()),
                    () -> assertEquals(expectedProduct.getName(), productCaptor.getValue().getName()),
                    () -> assertEquals(expectedProduct.getDescription(), productCaptor.getValue().getDescription()),
                    () -> assertEquals(expectedProduct.getPrice(), productCaptor.getValue().getPrice()),
                    () -> assertEquals(expectedProduct.getCreated(), productCaptor.getValue().getCreated())
            );
        }

        @Test
        void checkUpdateShouldThrowProductNotFoundException() {
            // given
            UUID uuidFound = TEST_UUID_1;
            UUID uuidNotFound = TEST_UUID_2;

            Product actualProduct = ProductTestData.builder()
                    .withUuid(uuidFound)
                    .withName("пришёл")
                    .withDescription("изменяться")
                    .build().buildProduct();
            ProductDto actualProductDto = ProductTestData.builder()
                    .withName("вышел")
                    .withDescription("изменённым")
                    .build().buildProductDto();
            Product expectedProduct = ProductTestData.builder()
                    .withUuid(uuidFound)
                    .withName("вышел")
                    .withDescription("изменённым")
                    .build().buildProduct();

            doReturn(Optional.of(actualProduct))
                    .when(productRepository).findById(uuidFound);
            doReturn(expectedProduct)
                    .when(mapper).merge(actualProduct, actualProductDto);
            doReturn(expectedProduct)
                    .when(productRepository).save(expectedProduct);

            // when, than
            assertAll(
                    () -> assertDoesNotThrow(() -> productService.update(uuidFound, actualProductDto)),
                    () -> assertThrows(ProductNotFoundException.class, () -> productService.update(uuidNotFound, actualProductDto))
            );
        }
    }

    @Nested
    class DeleteTest {
        @ParameterizedTest
        @MethodSource("provideUuid")
        void checkDeleteShouldCalledRepositoryMethod(UUID argument) {
            // given, when
            productService.delete(argument);

            // than
            verify(productRepository, times(1)).delete(argument);
        }

        private static Stream<UUID> provideUuid() {
            return Stream.of(TEST_UUID_1, TEST_UUID_2, TEST_UUID_3);
        }
    }
}
