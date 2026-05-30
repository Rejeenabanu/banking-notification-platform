package com.bank.account.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequest {

    @NotNull
    private BigDecimal amount;
}