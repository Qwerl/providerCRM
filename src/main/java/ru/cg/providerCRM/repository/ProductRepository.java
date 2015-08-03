package ru.cg.providerCRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cg.providerCRM.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    Product findByName(String name);
    List<Product> findByIdNotIn(List<Long> productsId);
}
