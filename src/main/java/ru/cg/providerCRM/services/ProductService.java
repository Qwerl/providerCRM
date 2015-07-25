package ru.cg.providerCRM.services;

import ru.cg.providerCRM.entity.Product;

import java.util.List;

public interface ProductService {
    Product getById(Long id);
    Product getByName(String name);
    List<Product> getAllProduct();
    void addProduct(Product product);
}
