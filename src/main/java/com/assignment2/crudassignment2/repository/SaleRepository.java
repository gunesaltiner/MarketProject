package com.assignment2.crudassignment2.repository;

import com.assignment2.crudassignment2.model.Consumer;
import com.assignment2.crudassignment2.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    List<Sale> findByConsumerId(Long consumerId);
    Sale findByCode(Integer randomCode);
}
