package com.assignment2.crudassignment2.model.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddSaleRequest {

    String email;
    List<Integer> productCodeList = new ArrayList<>();

}
