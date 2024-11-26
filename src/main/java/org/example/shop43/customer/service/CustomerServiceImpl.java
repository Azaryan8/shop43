package org.example.shop43.customer.service;


import lombok.RequiredArgsConstructor;
import org.example.shop43.cart.entity.Cart;
import org.example.shop43.customer.dto.CustomerRequestDto;
import org.example.shop43.customer.dto.CustomerResponseDto;
import org.example.shop43.customer.entityy.Customer;
import org.example.shop43.customer.repository.CustomerRepository;
import org.example.shop43.exception.CustomerNotFoundException;
import org.example.shop43.product.entity.Product;
import org.example.shop43.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository repository;
    private final ProductService productService;
    private final ModelMapper mapper;


    @Override

    public CustomerResponseDto createCustomer(CustomerRequestDto dto) {
        Customer entity = mapper.map(dto, Customer.class);
        entity.setActive(true);
        Cart cart = new Cart();
        entity.setCart(cart);       // java relation
        cart.setCustomer(entity);

        entity = repository.save(entity);
        CustomerResponseDto customerResponseDto = mapper.map(entity, CustomerResponseDto.class);
        return customerResponseDto;
    }

    @Override
    public List<CustomerResponseDto> getCustomers() {
        List<Customer> customers = repository.findAll();
        return customers.stream().map(c->mapper.map(c, CustomerResponseDto.class)).toList();
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        return mapper.map(findCustomerById(id), CustomerResponseDto.class);
    }

    private Customer findCustomerById(Long id) {
        String msg = "Customer id:" + id + " not found";
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(msg));
        return customer;
    }

    @Override
    @Transactional
    public CustomerResponseDto addProductToCart(Long customerId, Long productId) {
        Customer customer = findCustomerById(customerId);
        Product product = productService.findProductById(productId);
        customer.getCart().addProduct(product);
        return mapper.map(customer, CustomerResponseDto.class);
    }

    @Override
    @Transactional
    public CustomerResponseDto removeProductFromCart(Long customerId, Long productId) {
        Customer customer = findCustomerById(customerId);
        Product product = productService.findProductById(productId);

        customer.getCart().remove(product);

        return mapper.map(customer, CustomerResponseDto.class);
    }

    @Override
    @Transactional
    public CustomerResponseDto changeStatus(Long id, boolean active) {
        Customer customer = findCustomerById(id);
        customer.setActive(active);
        return mapper.map(customer, CustomerResponseDto.class);
    }
}
