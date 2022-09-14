package com.assignment2.crudassignment2.repository;

import com.assignment2.crudassignment2.model.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    Consumer findByName(String name);
    Consumer findByEmail(String email);
    String deleteByEmail(String email);
}
