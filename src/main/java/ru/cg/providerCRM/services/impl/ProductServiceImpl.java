package ru.cg.providerCRM.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cg.providerCRM.entity.Product;
import ru.cg.providerCRM.repository.ProductRepository;
import ru.cg.providerCRM.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getById(Long id) {
        return productRepository.findOne(id);
    }

    public Product getByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    public List<Product> getProductsNotIn(List<Product> products) {

        if (products.isEmpty()) {
            return getAllProduct();
        }
        List<Long> productsId = new ArrayList<>();
        for (Product product : products) {
            productsId.add(product.getId());
        }
        return productRepository.findByIdNotIn(productsId);
    }
}
