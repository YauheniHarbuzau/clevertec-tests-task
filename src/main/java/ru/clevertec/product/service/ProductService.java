package ru.clevertec.product.service;

import ru.clevertec.product.data.InfoProductDto;
import ru.clevertec.product.data.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    InfoProductDto get(UUID uuid);

    List<InfoProductDto> getAll();

    UUID create(ProductDto productDto);

    void update(UUID uuid, ProductDto productDto);

    void delete(UUID uuid);
}
