package com.assignment2.crudassignment2.service;

import com.assignment2.crudassignment2.model.dto.CustomerDto;
import com.assignment2.crudassignment2.model.request.ConsumerRequest;

import java.util.List;

public interface CustomerService {
    CustomerDto saveConsumer(ConsumerRequest consumerRequest);

    CustomerDto updateConsumer(ConsumerRequest consumerRequest, String newEmail) throws Exception;

    void deleteConsumerByEmail(String email);

    String deleteConsumerById(Long id);

    CustomerDto getConsumerByName(String name) throws Exception;

    CustomerDto getConsumerById(Long id) throws Exception;

    List<CustomerDto> getConsumers();
}
