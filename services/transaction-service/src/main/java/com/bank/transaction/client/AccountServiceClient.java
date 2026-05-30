package com.bank.transaction.client;

import com.bank.transaction.client.dto.AccountResponse;
import com.bank.transaction.dto.DepositRequest;
import com.bank.transaction.dto.TransferRequest;
import com.bank.transaction.dto.WithdrawRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(
        name = "account-service",
        url = "http://localhost:8082")
public interface AccountServiceClient {

    @GetMapping("/api/accounts/{id}")
    AccountResponse getAccount(
            @PathVariable UUID id);

    @PostMapping("/api/accounts/{id}/deposit")
    AccountResponse deposit(
            @PathVariable UUID id,
            @RequestBody DepositRequest request);

    @PostMapping("/api/accounts/{id}/withdraw")
    AccountResponse withdraw(
            @PathVariable UUID id,
            @RequestBody WithdrawRequest request);

    @PostMapping("/api/accounts/transfer")
    void transfer(
            @RequestBody TransferRequest request);
}