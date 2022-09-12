package com.assignment2.crudassignment2.service.impl;

import com.assignment2.crudassignment2.exception.NotFoundException;
import com.assignment2.crudassignment2.model.Product;
import com.assignment2.crudassignment2.model.dto.ProductDto;
import com.assignment2.crudassignment2.model.request.AddProductRequest;
import com.assignment2.crudassignment2.model.request.UpdateProductRequest;
import com.assignment2.crudassignment2.repository.ProductRepository;
import com.assignment2.crudassignment2.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final String message ="The object you were looking for was not found.";
    private final ProductRepository productRepository;

    public ProductDto productDtoConverter(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setPrice(product.getPrice());
        productDto.setName(product.getName());
        productDto.setCode(product.getCode());
        return productDto;
    }

    public Integer createRandomCode() {
        Random random = new Random();
        Integer randomCode = random.nextInt(90000) + 10000;

        while (productRepository.findByCode(randomCode) != null) {
            randomCode = random.nextInt(90000) + 10000;
        }
        return randomCode;
    }

    public ProductDto saveProduct(AddProductRequest addProductRequest) {
        Product product = new Product();
        product.setPrice(addProductRequest.getPrice());
        product.setName(addProductRequest.getName());
        product.setCode(createRandomCode());
        productRepository.save(product);

        return productDtoConverter(product);
    }

    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> resultList = new ArrayList<>();
        for (Product product : products) {
            resultList.add(productDtoConverter(product));
        }
        return resultList;
    }

    public ProductDto getProductByCode(Integer code) throws NotFoundException {
        Optional<Product> productOptional = Optional.ofNullable(productRepository.findByCode(code));
        if (productOptional == null) {
            throw new NotFoundException(message);
        }
        return productDtoConverter(productOptional.get());
    }

    public ProductDto getProductByName(String name) throws NotFoundException {
        Optional<Product> productOptional = Optional.ofNullable(productRepository.findByName(name));
        if (productOptional == null) {
            throw new NotFoundException(message);
        }
        return productDtoConverter(productOptional.get());
    }

    public void deleteProduct(Integer code) {
        productRepository.deleteByCode(code);
    }

    public ProductDto updateProduct(UpdateProductRequest updateProductRequest, int code) throws NotFoundException {
        Optional<Product> optionalProduct = Optional.ofNullable(productRepository.findByCode(code));
        if (!optionalProduct.isPresent()) {
            throw new NotFoundException(message);
        }
        Product updatedProduct = optionalProduct.get();
        updatedProduct.setPrice(updateProductRequest.getPrice());
        updatedProduct.setName(updateProductRequest.getName());
        productRepository.save(updatedProduct);

        return productDtoConverter(updatedProduct);
    }

}
