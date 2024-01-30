package com.salesapi.sales.controller;

import com.salesapi.sales.entity.Product;
import com.salesapi.sales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public List<Product> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/products/{productId}")
    public Product getProductByProductId(@PathVariable int productId) {
        return service.getProductByProductId(productId);
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product){
        service.addProduct(product);
    }

    @PutMapping("/products")
    public void updateProductByProductId(@RequestBody Product product){
        service.updateProduct(product);
    }

    @DeleteMapping("/products/{productId}")
    public void deleteProductByProductId(@PathVariable int productId){
        service.deleteProductByProductId(productId);
    }
}
