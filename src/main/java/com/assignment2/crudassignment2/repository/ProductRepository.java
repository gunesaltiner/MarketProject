package com.assignment2.crudassignment2.repository;

import com.assignment2.crudassignment2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);
    Product findByCode(Integer code);

    List<Product> findByCodeIn(List<Integer> productCodes);

    void deleteByCode(Integer code);
}
