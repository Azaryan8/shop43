package org.example.shop43.customer.repository;

import org.example.shop43.customer.entityy.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
