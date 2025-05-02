package dev.panachai.customer.repository;

import dev.panachai.customer.model.Customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerName(String customerName);
    void deleteByCustomerName(String customerName);

}
