package com.assignment2.crudassignment2.controller;

import com.assignment2.crudassignment2.model.dto.ConsumerDto;
import com.assignment2.crudassignment2.model.request.ConsumerRequest;
import com.assignment2.crudassignment2.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class ConsumerController {

    private final ConsumerService consumerService;

    // POST METHODS
    @PostMapping("/consumer/save")
    public ResponseEntity<ConsumerDto> addConsumer(@Valid @RequestBody ConsumerRequest consumerRequest) {
        return new ResponseEntity<>(consumerService.saveConsumer(consumerRequest), HttpStatus.CREATED);
    }

    // PUT METHOD
    @PutMapping("/consumer/update/{email}")
    public ResponseEntity<ConsumerDto> updateConsumer(@RequestBody ConsumerRequest consumerRequest, @PathVariable String email) throws Exception {
        return new ResponseEntity<>(consumerService.updateConsumer(consumerRequest,email), HttpStatus.ACCEPTED);
    }

    // DELETE METHODS
    @DeleteMapping("/consumer/delete/email/{email}")
    public ResponseEntity<Void> deleteConsumerByEmail(@PathVariable String email) {
        consumerService.deleteConsumerByEmail(email);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/consumer/delete/{id}")
    public ResponseEntity<Void> deleteConsumerById(@PathVariable Long id) {
        consumerService.deleteConsumerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // GET METHODS
    @GetMapping("/consumer/id/{id}")
    public ResponseEntity<ConsumerDto> findConsumerById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(consumerService.getConsumerById(id), HttpStatus.FOUND);
    }

    @GetMapping("/consumer/{name}")
    public ResponseEntity<ConsumerDto> findConsumerByName(@PathVariable String name) throws Exception {
        return new ResponseEntity<>(consumerService.getConsumerByName(name), HttpStatus.FOUND);
    }

    @GetMapping("/consumers")
    public ResponseEntity<List<ConsumerDto>> findConsumers() {
        return new ResponseEntity<>(consumerService.getConsumers(), HttpStatus.FOUND) ;
    }
}
