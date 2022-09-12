package com.assignment2.crudassignment2.model.request;

import com.assignment2.crudassignment2.model.Consumer;
import com.assignment2.crudassignment2.model.Product;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UpdateSaleRequest {

    private Integer oldProductCode;

    private Integer newProductCode;

}
