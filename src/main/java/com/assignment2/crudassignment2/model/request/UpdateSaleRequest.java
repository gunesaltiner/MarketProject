package com.assignment2.crudassignment2.model.request;

import lombok.Data;

@Data
public class UpdateSaleRequest {

    private Integer oldProductCode;

    private Integer newProductCode;

}
