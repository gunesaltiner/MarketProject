package com.assignment2.crudassignment2.service;

import com.assignment2.crudassignment2.model.Product;
import com.assignment2.crudassignment2.model.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto saveProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto, Double Price) throws Exception;

    void deleteProduct(Integer code);

    ProductDto getProductByName(String name) throws Exception;

    ProductDto getProductByCode(Integer code) throws Exception;

    List<ProductDto> getProducts();
}
