package com.assignment2.crudassignment2.service;

import com.assignment2.crudassignment2.model.dto.ConsumerDto;
import com.assignment2.crudassignment2.model.request.ConsumerRequest;

import java.util.List;

public interface ConsumerService {
    ConsumerDto saveConsumer(ConsumerRequest consumerRequest);

    ConsumerDto updateConsumer(ConsumerRequest consumerRequest,String newEmail) throws Exception;

    void deleteConsumerByEmail(String email);

    String deleteConsumerById(Long id);

    ConsumerDto getConsumerByName(String name) throws Exception;

    ConsumerDto getConsumerById(Long id) throws Exception;

    List<ConsumerDto> getConsumers();
}
