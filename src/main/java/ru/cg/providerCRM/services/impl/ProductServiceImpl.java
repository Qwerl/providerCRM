package ru.cg.providerCRM.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cg.providerCRM.entity.Product;
import ru.cg.providerCRM.repository.ProductRepository;
import ru.cg.providerCRM.services.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    public Product getById(Long id) {
        return productRepository.findOne(id);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}