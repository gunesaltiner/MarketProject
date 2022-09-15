package com.assignment2.crudassignment2.service.impl;

import com.assignment2.crudassignment2.exception.NotFoundException;
import com.assignment2.crudassignment2.model.entity.Customer;
import com.assignment2.crudassignment2.model.dto.CustomerDto;
import com.assignment2.crudassignment2.model.request.ConsumerRequest;
import com.assignment2.crudassignment2.repository.CustomerRepository;
import com.assignment2.crudassignment2.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class CustomerServiceImpl implements CustomerService {

    private final String message ="The object you were looking for was not found.";
    private final CustomerRepository customerRepository;

    public CustomerDto saveConsumer(ConsumerRequest consumerRequest) {

        Customer customer = new Customer();
        customer.setEmail(consumerRequest.getEmail());
        customer.setName(consumerRequest.getName());
        customerRepository.save(customer);

        return consumerDtoConverter(customer);
    }

    public CustomerDto consumerDtoConverter(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        return customerDto;
    }

    public CustomerDto updateConsumer(ConsumerRequest consumerRequest, String email) throws NotFoundException {
        Optional<Customer> optionalConsumer = Optional.ofNullable(customerRepository.findByEmail(email));
        if (!optionalConsumer.isPresent()) {
            throw new NotFoundException(message);
        }
        Customer updatedCustomer = optionalConsumer.get();
        updatedCustomer.setEmail(consumerRequest.getEmail());
        updatedCustomer.setName(consumerRequest.getName());
        customerRepository.save(updatedCustomer);

        return consumerDtoConverter(updatedCustomer);
    }

    public void deleteConsumerByEmail(String email) {
        customerRepository.deleteByEmail(email);
    }

    public String deleteConsumerById(Long id) {
        customerRepository.deleteById(id);
        return "Consumer has removed !";
    }

    public CustomerDto getConsumerByName(String name) throws NotFoundException {
        Optional<Customer> consumerOptional = Optional.ofNullable(customerRepository.findByName(name));
        if (!consumerOptional.isPresent()) {
            throw new NotFoundException(message);
        }
        return consumerDtoConverter(consumerOptional.get());
    }

    public CustomerDto getConsumerById(Long id) throws NotFoundException {
        Optional<Customer> consumerOptional = customerRepository.findById(id);

        if (!consumerOptional.isPresent()) {
            throw new NotFoundException(message);
        }
        return consumerDtoConverter(consumerOptional.get());
    }

    public List<CustomerDto> getConsumers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customerList) {
            customerDtos.add(consumerDtoConverter(customer));
        }

        return customerDtos;
    }
}
