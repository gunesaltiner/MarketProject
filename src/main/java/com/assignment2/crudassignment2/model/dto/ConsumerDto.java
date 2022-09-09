package com.assignment2.crudassignment2.model.dto;

import com.assignment2.crudassignment2.model.Sale;
import lombok.Data;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
public class ConsumerDto {

    @Email
    private String email;

    @NotEmpty
    private String name;

    private Set<Sale> purchased = new HashSet<>();
}
