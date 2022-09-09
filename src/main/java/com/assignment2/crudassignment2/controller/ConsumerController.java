package com.assignment2.crudassignment2.controller;

import com.assignment2.crudassignment2.model.Consumer;
import com.assignment2.crudassignment2.model.dto.ConsumerDto;
import com.assignment2.crudassignment2.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/consumer")
@Validated
public class ConsumerController {

    private final ConsumerService consumerService;

    // POST METHODS
    @PostMapping("/save/{name}")
    public ResponseEntity<ConsumerDto> addConsumer(@RequestBody ConsumerDto consumerDto) {
        //Donusler DTO olacak validasyonları guncelle hata mesajları ekle
        //Product valid mi urun valid gibi gibi seylere hata fırlat response olarak duzgun bir hata mesajı içinde don
        return new ResponseEntity<>(consumerService.saveConsumer(consumerDto), HttpStatus.CREATED);
    }

    // PUT METHOD
    @PutMapping("/update")
    public ResponseEntity<ConsumerDto> updateConsumer(@RequestBody ConsumerDto consumerDto, @PathVariable String newEmail) throws Exception {
        return new ResponseEntity<>(consumerService.updateConsumer(consumerDto,newEmail), HttpStatus.ACCEPTED);
    }

    // DELETE METHODS
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> deleteConsumerByEmail(@PathVariable @Email String email) {
        consumerService.deleteConsumerByEmail(email);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConsumerById(@PathVariable Long id) {
        consumerService.deleteConsumerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // GET METHODS
    @GetMapping("/consumer/{id}")
    public ResponseEntity<ConsumerDto> findConsumerById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(consumerService.getConsumerById(id), HttpStatus.FOUND);
    }

    @GetMapping("/consumer/byname/{name}")
    public ResponseEntity<ConsumerDto> findConsumerByName(@PathVariable String name) throws Exception {
        return new ResponseEntity<>(consumerService.getConsumerByName(name), HttpStatus.FOUND);
    }

    @GetMapping("/consumers")
    public ResponseEntity<List<ConsumerDto>> findConsumers() {
        return new ResponseEntity<>(consumerService.getConsumers(), HttpStatus.FOUND) ;
    }
}
