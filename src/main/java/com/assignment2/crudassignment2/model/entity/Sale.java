package com.assignment2.crudassignment2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.boot.JaccPermissionDefinition;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany()
    private Set<Product> products = new HashSet<>();

    @ManyToOne
    private Consumer consumer;

    private double totalCost;

    @Column(unique = true)
    private Integer code;
}
