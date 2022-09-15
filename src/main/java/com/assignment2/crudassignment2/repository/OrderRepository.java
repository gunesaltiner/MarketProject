package com.assignment2.crudassignment2.repository;

import com.assignment2.crudassignment2.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByConsumerId(Long consumerId);
    Order findByCode(Integer randomCode);
}
