package com.assignment2.crudassignment2.service.impl;

import com.assignment2.crudassignment2.exception.NotFoundException;
import com.assignment2.crudassignment2.model.entity.Consumer;
import com.assignment2.crudassignment2.model.entity.Product;
import com.assignment2.crudassignment2.model.entity.Sale;
import com.assignment2.crudassignment2.model.dto.SaleDto;
import com.assignment2.crudassignment2.model.request.AddSaleRequest;
import com.assignment2.crudassignment2.model.request.UpdateSaleRequest;
import com.assignment2.crudassignment2.repository.ConsumerRepository;
import com.assignment2.crudassignment2.repository.ProductRepository;
import com.assignment2.crudassignment2.repository.SaleRepository;
import com.assignment2.crudassignment2.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final ConsumerRepository consumerRepository;

    private final String message ="The object you were looking for was not found.";

    public SaleDto saveSale(AddSaleRequest addSaleRequest) throws Exception {

        Optional<List<Product>> productListOptional = Optional.ofNullable(productRepository.findByCodeIn(addSaleRequest.getProductCodeList()));
//TODO check products
        if (!productListOptional.isPresent()) {
            throw new Exception(message);
        }
        Optional<Consumer> consumerOptional = Optional.ofNullable(consumerRepository.findByEmail(addSaleRequest.getEmail()));

        if (!consumerOptional.isPresent()) {
            throw new Exception(message);
        }

        List<Product> productList = productListOptional.get();
        Sale sale = new Sale();

        double totalCost = 0;
        for (Product product : productList) {
            sale.getProducts().add(product);
            totalCost = totalCost + product.getPrice();
        }

        Random random = new Random();
        int randomCode = random.nextInt(90000) + 10000;

        while (saleRepository.findByCode(randomCode) != null) {
            randomCode = random.nextInt(90000) + 10000;
        }

        sale.setCode(randomCode);
        sale.setTotalCost(totalCost);
        sale.setConsumer(consumerOptional.get());
        saleRepository.save(sale);

        return saleDtoConverter(sale);
    }

    public SaleDto updateSale(UpdateSaleRequest updateSaleRequest, Integer saleCode) throws Exception {
        Optional<Sale> sale = Optional.ofNullable(saleRepository.findByCode(saleCode));
        if (!sale.isPresent()) {
            throw new Exception(message);
        }

        Optional<Product> oldProduct = Optional.ofNullable(productRepository.findByCode(updateSaleRequest.getOldProductCode()));

        if (!oldProduct.isPresent()) {
            throw new Exception(message);
        }

        Optional<Product> newProduct = Optional.ofNullable(productRepository.findByCode(updateSaleRequest.getNewProductCode()));
        if (!newProduct.isPresent()) {
            throw new Exception(message);
        }

        double totalCost = sale.get().getTotalCost() + newProduct.get().getPrice()
                - oldProduct.get().getPrice();

        sale.get().setTotalCost(totalCost);
        sale.get().getProducts().remove(oldProduct.get());
        sale.get().getProducts().add(newProduct.get());
        saleRepository.save(sale.get());

        return saleDtoConverter(sale.get());
    }

    public void deleteSale(Integer code) throws NotFoundException {
        Optional<Sale> sale = Optional.ofNullable(saleRepository.findByCode(code));
        if (!sale.isPresent()) {
            throw new NotFoundException(message);
        }
        saleRepository.deleteById(sale.get().getId());
    }

    public SaleDto getSaleByCode(Integer code) throws NotFoundException {
        Optional<Sale> sale = Optional.ofNullable(saleRepository.findByCode(code));
        if (!sale.isPresent()) {
            throw new NotFoundException(message);
        }
        return saleDtoConverter(sale.get());
    }

    public List<SaleDto> getSalesByConsumerEmail(String consumerEmail) throws NotFoundException {
        Optional<Consumer> consumer = Optional.ofNullable(consumerRepository.findByEmail(consumerEmail));
        if (!consumer.isPresent()) {
            throw new NotFoundException(message);
        }

        List<Sale> sales = saleRepository.findByConsumerId((consumer.get().getId()));

        List<SaleDto> resultList = new ArrayList<>();
        for (Sale sale : sales) {
            SaleDto saleDto = saleDtoConverter(sale);
            resultList.add(saleDto);
        }
        return resultList;
    }

    public List<SaleDto> getSales() {
        List<Sale> sales = saleRepository.findAll();
        List<SaleDto> resultList = new ArrayList<>();
        for (Sale sale : sales) {
            SaleDto saleDto = saleDtoConverter(sale);
            resultList.add(saleDto);
        }
        return resultList;
    }

    public SaleDto saleDtoConverter(Sale sale) {
        SaleDto saleDto = new SaleDto();
        saleDto.setTotalCost(sale.getTotalCost());
        saleDto.setCode(sale.getCode());
        saleDto.setConsumer(sale.getConsumer());
        saleDto.setProducts(sale.getProducts());

        return saleDto;
    }
}
