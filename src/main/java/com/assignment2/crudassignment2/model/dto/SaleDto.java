package com.assignment2.crudassignment2.model.dto;

import com.assignment2.crudassignment2.model.entity.Consumer;
import com.assignment2.crudassignment2.model.entity.Product;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class SaleDto {

    private Set<Product> products = new HashSet<>();

    private Consumer consumer;

    private double totalCost;

    private Integer code;
}
