package com.bank.customer.service;

import com.bank.customer.domain.CreateCustomerRequest;
import com.bank.customer.domain.CustomerResponse;
import com.bank.customer.entity.Customer;
import com.bank.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerResponse createCustomer(
            CreateCustomerRequest request) {

        if(repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException(
                    "Email already exists");
        }

        Customer customer =
                Customer.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .phoneNumber(request.getPhoneNumber())
                        .build();

        Customer saved =
                repository.save(customer);

        return map(saved);
    }

    public CustomerResponse getCustomer(UUID id) {

        Customer customer =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceAccessException(
                                        "Customer not found"));

        return map(customer);
    }

    public List<CustomerResponse> getAllCustomers() {

        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    private CustomerResponse map(Customer customer) {

        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .createdAt(customer.getCreatedAt())
                .build();
    }
}
