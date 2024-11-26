package org.example.shop43.customer.service;


import org.example.shop43.customer.dto.CustomerRequestDto;
import org.example.shop43.customer.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    public CustomerResponseDto createCustomer(CustomerRequestDto dto);
    public List<CustomerResponseDto> getCustomers();
    public CustomerResponseDto getCustomerById(Long id);
    public CustomerResponseDto addProductToCart(Long customerId, Long productId);
    public CustomerResponseDto removeProductFromCart(Long customerId, Long productId);
    public CustomerResponseDto changeStatus(Long id, boolean active);


}

