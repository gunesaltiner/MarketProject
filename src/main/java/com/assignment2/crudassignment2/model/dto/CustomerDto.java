package com.assignment2.crudassignment2.model.dto;

import com.assignment2.crudassignment2.model.entity.Order;
import lombok.Data;


import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
public class CustomerDto {

    @Email
    private String email;

    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "consumer")
    private Set<Order> purchased = new HashSet<>();
}
