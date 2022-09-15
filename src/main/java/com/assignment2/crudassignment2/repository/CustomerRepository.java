package com.assignment2.crudassignment2.repository;

import com.assignment2.crudassignment2.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByName(String name);
    Customer findByEmail(String email);
    String deleteByEmail(String email);
}
