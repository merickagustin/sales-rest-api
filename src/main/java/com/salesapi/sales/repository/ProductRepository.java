package com.salesapi.sales.repository;

import com.salesapi.sales.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByProductId(int productId);
}
