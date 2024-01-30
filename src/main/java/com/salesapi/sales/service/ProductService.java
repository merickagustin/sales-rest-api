package com.salesapi.sales.service;

import com.salesapi.sales.entity.Product;
import com.salesapi.sales.interfaces.IProductService;
import com.salesapi.sales.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository repo;
    @Override
    public Product getProductByProductId(int productId) {
        return repo.findByProductId(productId);
    }

    @Override
    public List<Product> getProducts() {
        return repo.findAll();
    }

    @Override
    public void deleteProductByProductId(int productId) {
        repo.deleteById(productId);
    }

    @Override
    public void updateProduct(Product product) {
        repo.save(product);
    }

    @Override
    public void addProduct(Product product) {
        repo.save(product);
    }
}
