package com.assignment2.crudassignment2.controller;

import com.assignment2.crudassignment2.model.Product;
import com.assignment2.crudassignment2.model.dto.ProductDto;
import com.assignment2.crudassignment2.service.ProductService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Validated
public class ProductController {

    private final ProductService productService;

    //POST METHODS
    @PostMapping("/save")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.saveProduct(productDto), HttpStatus.CREATED);
    }


    // GET METHODS
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> findProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.FOUND);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> findProductByCode(@PathVariable Integer code) throws Exception {
        return new ResponseEntity<>(productService.getProductByCode(code), HttpStatus.FOUND);
    }

    @GetMapping("/product/byname/{name}")
    public ResponseEntity<ProductDto> findProductByName(@PathVariable String name) throws Exception {
        return new ResponseEntity<>(productService.getProductByName(name), HttpStatus.FOUND);
    }

    //PUT METHOD
    @PutMapping("/update")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable Double price) throws Exception {
        return new ResponseEntity<>(productService.updateProduct(productDto, price), HttpStatus.ACCEPTED);
    }

    // DELETE METHOD
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer code) {
        productService.deleteProduct(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
