package com.assignment2.crudassignment2.controller;

import com.assignment2.crudassignment2.model.dto.ProductDto;
import com.assignment2.crudassignment2.model.request.AddProductRequest;
import com.assignment2.crudassignment2.model.request.UpdateProductRequest;
import com.assignment2.crudassignment2.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class ProductController {

    private final ProductService productService;

    //POST METHODS
    @PostMapping("/product/save")
    public ResponseEntity<ProductDto> addProduct(@RequestBody AddProductRequest addProductRequest) {
        return new ResponseEntity<>(productService.saveProduct(addProductRequest), HttpStatus.CREATED);
    }


    // GET METHODS
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> findProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.FOUND);
    }

    @GetMapping("/product/{code}")
    public ResponseEntity<ProductDto> findProductByCode(@PathVariable Integer code) throws Exception {
        return new ResponseEntity<>(productService.getProductByCode(code), HttpStatus.FOUND);
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<ProductDto> findProductByName(@PathVariable String name) throws Exception {
        return new ResponseEntity<>(productService.getProductByName(name), HttpStatus.FOUND);
    }

    //PUT METHOD
    @PutMapping("/product/update/{code}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody UpdateProductRequest updateProductRequest, @PathVariable int code) throws Exception {
        return new ResponseEntity<>(productService.updateProduct(updateProductRequest, code), HttpStatus.ACCEPTED);
    }

    // DELETE METHOD
    @DeleteMapping("/product/delete/{code}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer code) {
        productService.deleteProduct(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
