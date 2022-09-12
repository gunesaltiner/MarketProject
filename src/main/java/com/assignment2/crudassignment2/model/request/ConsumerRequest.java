package com.assignment2.crudassignment2.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class ConsumerRequest {

    @Email
    private String email;

    @NotEmpty
    private String name;
}
