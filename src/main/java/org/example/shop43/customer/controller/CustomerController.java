package org.example.shop43.customer.controller;


import lombok.RequiredArgsConstructor;
import org.example.shop43.customer.dto.CustomerRequestDto;
import org.example.shop43.customer.dto.CustomerResponseDto;
import org.example.shop43.customer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")

public class CustomerController {
    private final CustomerService service;

    @PostMapping
    public CustomerResponseDto createCustomer(@RequestBody CustomerRequestDto dto) {
        return service.createCustomer(dto);
    }

    @GetMapping
    public List<CustomerResponseDto> getCustomers() {
        return service.getCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponseDto getCustomerById(@PathVariable(name="id") Long id) {
        return service.getCustomerById(id);
    }

    // customers/3/products/5
    @PutMapping("/{customerId}/products/{productId}")
    public CustomerResponseDto addProductToCart(@PathVariable(name="customerId") Long customerId,
                                                @PathVariable(name="productId") Long productId) {
        return service.addProductToCart(customerId,productId);
    }
    @DeleteMapping("/{customerId}/products/{productId}")
    public CustomerResponseDto removeProductFromCart(@PathVariable(name="customerId") Long customerId,
                                                     @PathVariable(name="productId") Long productId) {
        return service.removeProductFromCart(customerId,productId);
    }

    @PatchMapping("/{id}")
    public CustomerResponseDto changeStatus(@PathVariable(name="id") Long id,
                                            @RequestParam(name="active", defaultValue = "true") boolean active) {
        return service.changeStatus(id,active);
    }
}

