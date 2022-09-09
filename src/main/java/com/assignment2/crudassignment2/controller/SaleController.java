package com.assignment2.crudassignment2.controller;

import com.assignment2.crudassignment2.model.Sale;
import com.assignment2.crudassignment2.model.dto.SaleDto;
import com.assignment2.crudassignment2.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Email;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sale")
public class SaleController {

    private final SaleService saleService;

    // POST METHODS
    @PostMapping("/save")
    public SaleDto addSale(@RequestBody SaleDto saleDto, @PathVariable List<Integer> productCode, String email) throws Exception {
        return saleService.saveSale(saleDto,productCode,email);
    }

    // PUT METHODS
    @PutMapping("/update")
    public SaleDto updateSale(@RequestBody SaleDto saleDto, @PathVariable Integer newProductCode, Integer oldProductCode) throws Exception {
        return saleService.updateSale(saleDto, newProductCode, oldProductCode);
    }

    // DELETE METHODS
    @DeleteMapping("/delete/{id}")
    public void deleteSale(@PathVariable Integer code) throws Exception {
        saleService.deleteSale(code);

    }

    // GET METHODS
    @GetMapping("/sale/{id}")
    public SaleDto findSaleByCode(@PathVariable Integer code) throws Exception {
        return saleService.getSaleByCode(code);
    }

    @GetMapping("/sales")
    public List<SaleDto> findSales() {
        return saleService.getSales();
    }

    @GetMapping("/sale/byname/{id}")
    public List<SaleDto> findSalesByConsumerEmail(@PathVariable String consumerEmail) throws Exception {
        return saleService.getSalesByConsumerEmail(consumerEmail);
    }

}
