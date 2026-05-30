package com.bank.account.domain;

import com.bank.account.enums.AccountType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CreateAccountRequest {

    private UUID customerId;

    private AccountType accountType;

    private BigDecimal initialDeposit;
}
