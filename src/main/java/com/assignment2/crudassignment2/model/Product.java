package com.assignment2.crudassignment2.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Double price;

    @Column(unique = true)
    private Integer code;
}
