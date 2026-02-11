package com.BlueAndPurple.products.service;

import com.BlueAndPurple.products.model.Products;
import com.BlueAndPurple.products.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ProductsService {
    private final ProductsRepository repository;

    public ProductsService(ProductsRepository repository) {
        this.repository = repository;
    }

    public Products saveProducts(Products product){
        return repository.save(product);
    }

    public Optional<Products> getByCode(Long codigo){
        return repository.findById(codigo);
    }

    public void delete(Products products) {
        products.setAtivo(false);
        repository.save(products);
    }
}
