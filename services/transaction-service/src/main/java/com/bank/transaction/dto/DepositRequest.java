package com.bank.transaction.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class DepositRequest {

    @NotNull
    private UUID accountId;

    @NotNull
    @Positive
    private BigDecimal amount;
}