package com.assignment2.crudassignment2.service;

import com.assignment2.crudassignment2.model.dto.OrderDto;
import com.assignment2.crudassignment2.model.request.AddSaleRequest;
import com.assignment2.crudassignment2.model.request.UpdateSaleRequest;

import java.util.List;

public interface OrderService {
    OrderDto saveSale(AddSaleRequest addSaleRequest) throws Exception;

    OrderDto updateSale(UpdateSaleRequest updateSaleRequest, Integer saleCode) throws Exception;

    void deleteSale(Integer code) throws Exception;

    OrderDto getSaleByCode(Integer code) throws Exception;

    List<OrderDto> getSalesByConsumerEmail(String consumerEmail) throws Exception;

    List<OrderDto> getSales();
}
