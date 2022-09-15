package com.assignment2.crudassignment2.controller;

import com.assignment2.crudassignment2.model.dto.CustomerDto;
import com.assignment2.crudassignment2.model.request.ConsumerRequest;
import com.assignment2.crudassignment2.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    // POST METHODS
    @PostMapping("/consumer/save")
    public ResponseEntity<CustomerDto> addConsumer(@Valid @RequestBody ConsumerRequest consumerRequest) {
        return new ResponseEntity<>(customerService.saveConsumer(consumerRequest), HttpStatus.CREATED);
    }

    // PUT METHOD
    @PutMapping("/consumer/update/{email}")
    public ResponseEntity<CustomerDto> updateConsumer(@RequestBody ConsumerRequest consumerRequest, @PathVariable String email) throws Exception {
        return new ResponseEntity<>(customerService.updateConsumer(consumerRequest,email), HttpStatus.ACCEPTED);
    }

    // DELETE METHODS
    @DeleteMapping("/consumer/delete/email/{email}")
    public ResponseEntity<Void> deleteConsumerByEmail(@PathVariable String email) {
        customerService.deleteConsumerByEmail(email);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/consumer/delete/{id}")
    public ResponseEntity<Void> deleteConsumerById(@PathVariable Long id) {
        customerService.deleteConsumerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // GET METHODS
    @GetMapping("/consumer/id/{id}")
    public ResponseEntity<CustomerDto> findConsumerById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(customerService.getConsumerById(id), HttpStatus.FOUND);
    }

    @GetMapping("/consumer/{name}")
    public ResponseEntity<CustomerDto> findConsumerByName(@PathVariable String name) throws Exception {
        return new ResponseEntity<>(customerService.getConsumerByName(name), HttpStatus.FOUND);
    }

    @GetMapping("/consumers")
    public ResponseEntity<List<CustomerDto>> findConsumers() {
        return new ResponseEntity<>(customerService.getConsumers(), HttpStatus.FOUND) ;
    }
}
