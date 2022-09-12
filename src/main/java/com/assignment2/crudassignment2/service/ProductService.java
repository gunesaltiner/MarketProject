package com.assignment2.crudassignment2.service;

import com.assignment2.crudassignment2.model.Product;
import com.assignment2.crudassignment2.model.dto.ProductDto;
import com.assignment2.crudassignment2.model.request.AddProductRequest;
import com.assignment2.crudassignment2.model.request.UpdateProductRequest;

import java.util.List;

public interface ProductService {

    ProductDto saveProduct(AddProductRequest addProductRequest);

    ProductDto updateProduct(UpdateProductRequest updateProductRequest, int code) throws Exception;

    void deleteProduct(Integer code);

    ProductDto getProductByName(String name) throws Exception;

    ProductDto getProductByCode(Integer code) throws Exception;

    List<ProductDto> getProducts();
}
