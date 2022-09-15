package com.assignment2.crudassignment2.service.impl;

import com.assignment2.crudassignment2.exception.NotFoundException;
import com.assignment2.crudassignment2.model.entity.Customer;
import com.assignment2.crudassignment2.model.entity.Product;
import com.assignment2.crudassignment2.model.entity.Order;
import com.assignment2.crudassignment2.model.dto.OrderDto;
import com.assignment2.crudassignment2.model.request.AddSaleRequest;
import com.assignment2.crudassignment2.model.request.UpdateSaleRequest;
import com.assignment2.crudassignment2.repository.CustomerRepository;
import com.assignment2.crudassignment2.repository.ProductRepository;
import com.assignment2.crudassignment2.repository.OrderRepository;
import com.assignment2.crudassignment2.service.OrderService;
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
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    private final String message ="The object you were looking for was not found.";

    public OrderDto saveSale(AddSaleRequest addSaleRequest) throws Exception {

        Optional<List<Product>> productListOptional = Optional.ofNullable(productRepository.findByCodeIn(addSaleRequest.getProductCodeList()));
//TODO check products
        if (!productListOptional.isPresent()) {
            throw new Exception(message);
        }
        Optional<Customer> consumerOptional = Optional.ofNullable(customerRepository.findByEmail(addSaleRequest.getEmail()));

        if (!consumerOptional.isPresent()) {
            throw new Exception(message);
        }

        List<Product> productList = productListOptional.get();
        Order order = new Order();

        double totalCost = 0;
        for (Product product : productList) {
            order.getProducts().add(product);
            totalCost = totalCost + product.getPrice();
        }

        Random random = new Random();
        int randomCode = random.nextInt(90000) + 10000;

        while (orderRepository.findByCode(randomCode) != null) {
            randomCode = random.nextInt(90000) + 10000;
        }

        order.setCode(randomCode);
        order.setTotalCost(totalCost);
        order.setCustomer(consumerOptional.get());
        orderRepository.save(order);

        return saleDtoConverter(order);
    }

    public OrderDto updateSale(UpdateSaleRequest updateSaleRequest, Integer saleCode) throws Exception {
        Optional<Order> sale = Optional.ofNullable(orderRepository.findByCode(saleCode));
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
        orderRepository.save(sale.get());

        return saleDtoConverter(sale.get());
    }

    public void deleteSale(Integer code) throws NotFoundException {
        Optional<Order> sale = Optional.ofNullable(orderRepository.findByCode(code));
        if (!sale.isPresent()) {
            throw new NotFoundException(message);
        }
        orderRepository.deleteById(sale.get().getId());
    }

    public OrderDto getSaleByCode(Integer code) throws NotFoundException {
        Optional<Order> sale = Optional.ofNullable(orderRepository.findByCode(code));
        if (!sale.isPresent()) {
            throw new NotFoundException(message);
        }
        return saleDtoConverter(sale.get());
    }

    public List<OrderDto> getSalesByConsumerEmail(String consumerEmail) throws NotFoundException {
        Optional<Customer> consumer = Optional.ofNullable(customerRepository.findByEmail(consumerEmail));
        if (!consumer.isPresent()) {
            throw new NotFoundException(message);
        }

        List<Order> orders = orderRepository.findByConsumerId((consumer.get().getId()));

        List<OrderDto> resultList = new ArrayList<>();
        for (Order order : orders) {
            OrderDto orderDto = saleDtoConverter(order);
            resultList.add(orderDto);
        }
        return resultList;
    }

    public List<OrderDto> getSales() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> resultList = new ArrayList<>();
        for (Order order : orders) {
            OrderDto orderDto = saleDtoConverter(order);
            resultList.add(orderDto);
        }
        return resultList;
    }

    public OrderDto saleDtoConverter(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setTotalCost(order.getTotalCost());
        orderDto.setCode(order.getCode());
        orderDto.setCustomer(order.getCustomer());
        orderDto.setProducts(order.getProducts());

        return orderDto;
    }
}
