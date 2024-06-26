package ru.clevertec.product.service.impl;

import lombok.RequiredArgsConstructor;
import ru.clevertec.product.data.InfoProductDto;
import ru.clevertec.product.data.ProductDto;
import ru.clevertec.product.entity.Product;
import ru.clevertec.product.exception.ProductNotFoundException;
import ru.clevertec.product.mapper.ProductMapper;
import ru.clevertec.product.repository.ProductRepository;
import ru.clevertec.product.service.ProductService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public InfoProductDto get(UUID uuid) {
        return productRepository.findById(uuid)
                .map(productMapper::toInfoProductDto)
                .orElseThrow(() -> new ProductNotFoundException(uuid));
    }

    @Override
    public List<InfoProductDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toInfoProductDto)
                .toList();
    }

    @Override
    public UUID create(ProductDto productDto) {
        Product productToCreate = productRepository.save(
                productMapper.toProduct(productDto));

        return productToCreate.getUuid();
    }

    @Override
    public void update(UUID uuid, ProductDto productDto) {
        Product productToUpdate = productRepository.findById(uuid)
                .map(product -> productMapper.merge(product, productDto))
                .orElseThrow(() -> new ProductNotFoundException(uuid));

        productRepository.save(productToUpdate);
    }

    @Override
    public void delete(UUID uuid) {
        productRepository.delete(uuid);
    }
}
