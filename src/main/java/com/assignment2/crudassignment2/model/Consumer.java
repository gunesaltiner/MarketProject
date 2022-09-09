package com.assignment2.crudassignment2.model;

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
@Table(name = "consumer")
public class Consumer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    @OneToMany(mappedBy = "consumer")
    private Set<Sale> purchased = new HashSet<>();
}
