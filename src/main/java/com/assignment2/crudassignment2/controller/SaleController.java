package com.assignment2.crudassignment2.controller;

import com.assignment2.crudassignment2.model.dto.SaleDto;
import com.assignment2.crudassignment2.model.request.AddSaleRequest;
import com.assignment2.crudassignment2.model.request.UpdateSaleRequest;
import com.assignment2.crudassignment2.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SaleController {

    private final SaleService saleService;

    // POST METHODS
    @PostMapping("/sale/save")
    public SaleDto addSale(@RequestBody AddSaleRequest addSaleRequest) throws Exception {
        return saleService.saveSale(addSaleRequest);
    }

    // PUT METHODS
    @PutMapping("/sale/update/{saleCode}")
    public SaleDto updateSale(@RequestBody UpdateSaleRequest updateSaleRequest, @PathVariable Integer saleCode) throws Exception {
        return saleService.updateSale(updateSaleRequest, saleCode);
    }

    // DELETE METHODS
    @DeleteMapping("/sale/delete/{code}")
    public void deleteSale(@PathVariable Integer code) throws Exception {
        saleService.deleteSale(code);
    }

    // GET METHODS
    @GetMapping("/sale/{code}")
    public SaleDto findSaleByCode(@PathVariable Integer code) throws Exception {
        return saleService.getSaleByCode(code);
    }

    @GetMapping("/sales")
    public List<SaleDto> findSales() {
        return saleService.getSales();
    }

    @GetMapping("/sale/{consumerEmail}")
    public List<SaleDto> findSalesByConsumerEmail(@PathVariable String consumerEmail) throws Exception {
        return saleService.getSalesByConsumerEmail(consumerEmail);
    }

}
