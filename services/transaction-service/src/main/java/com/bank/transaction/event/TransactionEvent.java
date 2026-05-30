package com.bank.transaction.event;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class TransactionEvent {

    private UUID transactionId;

    private UUID accountId;

    private BigDecimal amount;

    private String type;
}