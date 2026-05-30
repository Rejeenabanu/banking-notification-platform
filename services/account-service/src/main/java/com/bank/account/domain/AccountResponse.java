package com.bank.account.domain;

import com.bank.account.enums.AccountStatus;
import com.bank.account.enums.AccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class AccountResponse {

    private UUID accountId;

    private UUID customerId;

    private String accountNumber;

    private BigDecimal balance;

    private AccountType accountType;

    private AccountStatus status;

    private LocalDateTime createdAt;
}
