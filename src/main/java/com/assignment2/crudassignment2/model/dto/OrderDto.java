package com.assignment2.crudassignment2.model.dto;

import com.assignment2.crudassignment2.model.entity.Customer;
import com.assignment2.crudassignment2.model.entity.Product;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDto {

    @OneToMany
    @JoinColumn(name = "fk_order")
    private Set<Product> products = new HashSet<>();

    private Customer customer;

    private double totalCost;

    private Integer code;
}
