package com.assignment2.crudassignment2.service;

import com.assignment2.crudassignment2.model.Consumer;
import com.assignment2.crudassignment2.model.dto.ConsumerDto;

import java.util.List;

public interface ConsumerService {
    ConsumerDto saveConsumer(ConsumerDto consumerDto);

    ConsumerDto updateConsumer(ConsumerDto consumerDto,String newEmail) throws Exception;

    String deleteConsumerByEmail(String email);

    String deleteConsumerById(Long id);

    ConsumerDto getConsumerByName(String name) throws Exception;

    ConsumerDto getConsumerById(Long id) throws Exception;

    List<ConsumerDto> getConsumers();
}
