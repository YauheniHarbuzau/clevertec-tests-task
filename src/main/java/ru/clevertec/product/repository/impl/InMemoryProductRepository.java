package ru.clevertec.product.repository.impl;

import ru.clevertec.product.entity.Product;
import ru.clevertec.product.repository.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static ru.clevertec.product.util.ProductValidator.isValid;

public class InMemoryProductRepository implements ProductRepository {

    private final ProductData productData = new ProductData();
    private final Map<UUID, Product> productDataMap = productData.getProductDataMap();

    @Override
    public Optional<Product> findById(UUID uuid) {
        return productDataMap.containsKey(uuid) ?
                Optional.of(productDataMap.get(uuid)) :
                Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return productDataMap.values()
                .stream()
                .toList();
    }

    @Override
    public Product save(Product product) {
        if (product == null || !isValid(product)) {
            throw new IllegalArgumentException();
        }

        if (product.getUuid() == null) {
            product.setUuid(UUID.randomUUID());
        }

        productDataMap.put(product.getUuid(), product);
        return product;
    }

    @Override
    public void delete(UUID uuid) {
        productDataMap.remove(uuid);
    }
}
