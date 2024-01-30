package com.salesapi.sales.interfaces;

import com.salesapi.sales.entity.Product;

import java.util.List;

public interface IProductService {
    Product getProductByProductId(int productId);

    List<Product> getProducts();

    void deleteProductByProductId(int productId);

    void updateProduct(Product product);

    void addProduct(Product product);
}
