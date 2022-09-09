package com.assignment2.crudassignment2.service;

import com.assignment2.crudassignment2.model.dto.SaleDto;

import java.util.List;

public interface SaleService {
    SaleDto saveSale(SaleDto saleDto, List<Integer> productCode, String email) throws Exception;
    SaleDto updateSale(SaleDto saleDto, Integer newProductCode, Integer oldProductCode) throws Exception;
    void deleteSale(Integer code) throws Exception;
    SaleDto getSaleByCode(Integer code) throws Exception;
    List<SaleDto> getSalesByConsumerEmail(String consumerEmail) throws Exception;
    List<SaleDto> getSales();
}
