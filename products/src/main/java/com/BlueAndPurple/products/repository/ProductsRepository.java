package com.BlueAndPurple.products.repository;

import com.BlueAndPurple.products.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
