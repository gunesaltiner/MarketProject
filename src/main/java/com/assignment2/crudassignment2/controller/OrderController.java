package com.assignment2.crudassignment2.controller;

import com.assignment2.crudassignment2.model.dto.OrderDto;
import com.assignment2.crudassignment2.model.request.AddSaleRequest;
import com.assignment2.crudassignment2.model.request.UpdateSaleRequest;
import com.assignment2.crudassignment2.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    // POST METHODS
    @PostMapping("/sale/save")
    public ResponseEntity<OrderDto> addSale(@RequestBody AddSaleRequest addSaleRequest) throws Exception {
        return new ResponseEntity<>(orderService.saveSale(addSaleRequest), HttpStatus.CREATED) ;
    }

    // PUT METHODS
    @PutMapping("/sale/update/{saleCode}")
    public ResponseEntity<OrderDto> updateSale(@RequestBody UpdateSaleRequest updateSaleRequest, @PathVariable Integer saleCode) throws Exception {
        return new ResponseEntity<>(orderService.updateSale(updateSaleRequest, saleCode), HttpStatus.ACCEPTED) ;
    }

    // DELETE METHODS
    @DeleteMapping("/sale/delete/{code}")
    public ResponseEntity<Void> deleteSale(@PathVariable Integer code) throws Exception {
        orderService.deleteSale(code);
        return new ResponseEntity<>(HttpStatus.OK) ;
    }

    // GET METHODS
    @GetMapping("/sale/code/{code}")
    public ResponseEntity<OrderDto> findSaleByCode(@PathVariable Integer code) throws Exception {
        return new ResponseEntity<>(orderService.getSaleByCode(code), HttpStatus.FOUND) ;
    }

    @GetMapping("/sales")
    public ResponseEntity<List<OrderDto>> findSales() {
        return new ResponseEntity<>(orderService.getSales(), HttpStatus.FOUND);
    }

    @GetMapping("/sale/{consumerEmail}")
    public ResponseEntity<List<OrderDto>> findSalesByConsumerEmail(@PathVariable String consumerEmail) throws Exception {
        return new ResponseEntity<>(orderService.getSalesByConsumerEmail(consumerEmail), HttpStatus.FOUND);
    }

}
