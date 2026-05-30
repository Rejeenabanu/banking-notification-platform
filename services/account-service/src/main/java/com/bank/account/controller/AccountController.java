package com.bank.account.controller;

import com.bank.account.domain.*;
import com.bank.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Tag(
        name = "Account Management",
        description = "Bank Account Operations")
public class AccountController {

    private final AccountService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Account")
    public AccountResponse createAccount(
            @Valid
            @RequestBody
            CreateAccountRequest request) {

        return service.createAccount(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Account")
    public AccountResponse getAccount(
            @PathVariable UUID id) {

        return service.getAccount(id);
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get Accounts By Customer")
    public List<AccountResponse> getAccountsByCustomer(
            @PathVariable UUID customerId) {

        return service.getAccountsByCustomer(customerId);
    }

    @PutMapping("/{id}/freeze")
    @Operation(summary = "Freeze Account")
    public AccountResponse freezeAccount(
            @PathVariable UUID id) {

        return service.freezeAccount(id);
    }

    @PutMapping("/{id}/close")
    @Operation(summary = "Close Account")
    public AccountResponse closeAccount(
            @PathVariable UUID id) {

        return service.closeAccount(id);
    }

    @PostMapping("/{id}/deposit")
    public AccountResponse deposit(
            @PathVariable UUID id,
            @RequestBody DepositRequest request) {

        return service.deposit(
                id,
                request.getAmount());
    }

    @PostMapping("/{id}/withdraw")
    public AccountResponse withdraw(
            @PathVariable UUID id,
            @RequestBody WithdrawRequest request) {

        return service.withdraw(
                id,
                request.getAmount());
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.OK)
    public void transfer(
            @RequestBody TransferRequest request) {

        service.transfer(request);
    }
}