package ru.cg.providerCRM.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.cg.providerCRM.entity.Product;
import ru.cg.providerCRM.services.ProductService;

import java.util.List;

@Controller
@Scope("request")
@RequestMapping(value = "/")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.POST)
    @ResponseBody
    public Product getProduct(@PathVariable("productId") Long productId) {
        Product product = productService.getById(productId);
        return product;
    }

    @RequestMapping(value = "/product/getAll", method = RequestMethod.POST)
    @ResponseBody
    public List<Product> getAllProducts() {
        List<Product> products = productService.getAllProduct();
        return products;
    }

}