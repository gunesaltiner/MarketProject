package com.assignment2.crudassignment2.service;

import com.assignment2.crudassignment2.model.dto.SaleDto;
import com.assignment2.crudassignment2.model.request.AddSaleRequest;
import com.assignment2.crudassignment2.model.request.UpdateSaleRequest;

import java.util.List;

public interface SaleService {
    SaleDto saveSale(AddSaleRequest addSaleRequest) throws Exception;

    SaleDto updateSale(UpdateSaleRequest updateSaleRequest, Integer saleCode) throws Exception;

    void deleteSale(Integer code) throws Exception;

    SaleDto getSaleByCode(Integer code) throws Exception;

    List<SaleDto> getSalesByConsumerEmail(String consumerEmail) throws Exception;

    List<SaleDto> getSales();
}
