package com.assignment2.crudassignment2.service.impl;

import com.assignment2.crudassignment2.exception.NotFoundException;
import com.assignment2.crudassignment2.model.Consumer;
import com.assignment2.crudassignment2.model.dto.ConsumerDto;
import com.assignment2.crudassignment2.model.request.ConsumerRequest;
import com.assignment2.crudassignment2.repository.ConsumerRepository;
import com.assignment2.crudassignment2.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final String message ="The object you were looking for was not found.";
    private final ConsumerRepository consumerRepository;

    public ConsumerDto saveConsumer(ConsumerRequest consumerRequest) {

        Consumer consumer = new Consumer();
        consumer.setEmail(consumerRequest.getEmail());
        consumer.setName(consumerRequest.getName());
        consumerRepository.save(consumer);

        return consumerDtoConverter(consumer);
    }

    public ConsumerDto consumerDtoConverter(Consumer consumer) {
        ConsumerDto consumerDto = new ConsumerDto();
        consumerDto.setName(consumer.getName());
        consumerDto.setEmail(consumer.getEmail());
        return consumerDto;
    }

    public ConsumerDto updateConsumer(ConsumerRequest consumerRequest, String email) throws NotFoundException {
        Optional<Consumer> optionalConsumer = Optional.ofNullable(consumerRepository.findByEmail(email));
        if (!optionalConsumer.isPresent()) {
            throw new NotFoundException(message);
        }
        Consumer updatedConsumer = optionalConsumer.get();
        updatedConsumer.setEmail(consumerRequest.getEmail());
        updatedConsumer.setName(consumerRequest.getName());
        consumerRepository.save(updatedConsumer);

        return consumerDtoConverter(updatedConsumer);
    }

    public String deleteConsumerByEmail(String email) {
        consumerRepository.deleteByEmail(email);
        return "This consumer has deleted!";
    }

    public String deleteConsumerById(Long id) {
        consumerRepository.deleteById(id);
        return "Consumer has removed !";
    }

    public ConsumerDto getConsumerByName(String name) throws NotFoundException {
        Optional<Consumer> consumerOptional = Optional.ofNullable(consumerRepository.findByName(name));
        if (!consumerOptional.isPresent()) {
            throw new NotFoundException(message);
        }
        return consumerDtoConverter(consumerOptional.get());
    }

    public ConsumerDto getConsumerById(Long id) throws NotFoundException {
        Optional<Consumer> consumerOptional = consumerRepository.findById(id);

        if (!consumerOptional.isPresent()) {
            throw new NotFoundException(message);
        }
        return consumerDtoConverter(consumerOptional.get());
    }

    public List<ConsumerDto> getConsumers() {
        List<Consumer> consumerList = consumerRepository.findAll();
        List<ConsumerDto> consumerDtos = new ArrayList<>();
        for (Consumer consumer : consumerList) {
            consumerDtos.add(consumerDtoConverter(consumer));
        }

        return consumerDtos;
    }
}
