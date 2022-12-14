package com.assignment2.crudassignment2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "consumer")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @Email
    private String email;

    private String name;


    @OneToMany(mappedBy = "customer")
    private Set<Order> purchased = new HashSet<>();
}
