package com.bank.customer.controller;

import com.bank.customer.domain.CreateCustomerRequest;
import com.bank.customer.domain.CustomerResponse;
import com.bank.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(
            @Valid @RequestBody
            CreateCustomerRequest request) {

        return service.createCustomer(request);
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomer(
            @PathVariable UUID id) {

        return service.getCustomer(id);
    }

    @GetMapping
    public List<CustomerResponse> getCustomers() {

        return service.getAllCustomers();
    }
}
