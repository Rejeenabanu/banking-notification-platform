package com.bank.transaction.client.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AccountResponse {

    private UUID accountId;

    private UUID customerId;

    private String accountNumber;

    private BigDecimal balance;

    private String accountType;

    private String status;
}