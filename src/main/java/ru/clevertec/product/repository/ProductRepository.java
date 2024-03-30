package ru.clevertec.product.repository;

import ru.clevertec.product.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    Optional<Product> findById(UUID uuid);

    List<Product> findAll();

    Product save(Product product);

    void delete(UUID uuid);
}
