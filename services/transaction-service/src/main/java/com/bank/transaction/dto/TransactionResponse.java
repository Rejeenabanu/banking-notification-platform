package com.bank.transaction.dto;

import com.bank.transaction.enums.TransactionStatus;
import com.bank.transaction.enums.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TransactionResponse {

    private UUID transactionId;

    private UUID accountId;

    private UUID targetAccountId;

    private BigDecimal amount;

    private TransactionType transactionType;

    private TransactionStatus status;

    private String description;

    private LocalDateTime createdAt;
}