package com.assignment2.crudassignment2.controller;

import com.assignment2.crudassignment2.model.dto.SaleDto;
import com.assignment2.crudassignment2.model.request.AddSaleRequest;
import com.assignment2.crudassignment2.model.request.UpdateSaleRequest;
import com.assignment2.crudassignment2.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SaleController {

    private final SaleService saleService;

    // POST METHODS
    @PostMapping("/sale/save")
    public ResponseEntity<SaleDto> addSale(@RequestBody AddSaleRequest addSaleRequest) throws Exception {
        return new ResponseEntity<>(saleService.saveSale(addSaleRequest), HttpStatus.CREATED) ;
    }

    // PUT METHODS
    @PutMapping("/sale/update/{saleCode}")
    public ResponseEntity<SaleDto> updateSale(@RequestBody UpdateSaleRequest updateSaleRequest, @PathVariable Integer saleCode) throws Exception {
        return new ResponseEntity<>(saleService.updateSale(updateSaleRequest, saleCode), HttpStatus.ACCEPTED) ;
    }

    // DELETE METHODS
    @DeleteMapping("/sale/delete/{code}")
    public ResponseEntity<Void> deleteSale(@PathVariable Integer code) throws Exception {
        saleService.deleteSale(code);
        return new ResponseEntity<>(HttpStatus.OK) ;
    }

    // GET METHODS
    @GetMapping("/sale/code/{code}")
    public ResponseEntity<SaleDto> findSaleByCode(@PathVariable Integer code) throws Exception {
        return new ResponseEntity<>(saleService.getSaleByCode(code), HttpStatus.FOUND) ;
    }

    @GetMapping("/sales")
    public ResponseEntity<List<SaleDto>> findSales() {
        return new ResponseEntity<>(saleService.getSales(), HttpStatus.FOUND);
    }

    @GetMapping("/sale/{consumerEmail}")
    public ResponseEntity<List<SaleDto>> findSalesByConsumerEmail(@PathVariable String consumerEmail) throws Exception {
        return new ResponseEntity<>(saleService.getSalesByConsumerEmail(consumerEmail), HttpStatus.FOUND);
    }

}
