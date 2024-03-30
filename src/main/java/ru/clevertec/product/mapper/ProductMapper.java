package ru.clevertec.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.clevertec.product.data.InfoProductDto;
import ru.clevertec.product.data.ProductDto;
import ru.clevertec.product.entity.Product;

import java.time.LocalDateTime;

@Mapper(imports = LocalDateTime.class)
public interface ProductMapper {

    @Mapping(target = "created", expression = "java(LocalDateTime.now())")
    Product toProduct(ProductDto productDto);

    InfoProductDto toInfoProductDto(Product product);

    Product merge(@MappingTarget Product product, ProductDto productDto);
}
