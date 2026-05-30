package com.bank.transaction.controller;

import com.bank.transaction.dto.DepositRequest;
import com.bank.transaction.dto.TransactionResponse;
import com.bank.transaction.dto.TransferRequest;
import com.bank.transaction.dto.WithdrawRequest;
import com.bank.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Tag(
        name = "Transaction Management",
        description = "Banking Transaction APIs"
)
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Deposit Money")
    public TransactionResponse deposit(
            @Valid @RequestBody
            DepositRequest request)
            throws Exception {

        return service.deposit(request);
    }

    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Withdraw Money")
    public TransactionResponse withdraw(
            @Valid @RequestBody
            WithdrawRequest request)
            throws Exception {

        return service.withdraw(request);
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Transfer Money")
    public TransactionResponse transfer(
            @Valid @RequestBody
            TransferRequest request)
            throws Exception {

        return service.transfer(request);
    }
}