package com.assignment2.crudassignment2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sale")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    @JoinColumn(name = "fk_order")
    private Set<Product> products = new HashSet<>();


    @ManyToOne
    private Customer customer;

    private double totalCost;

    @Column(unique = true)
    private Integer code;
}
