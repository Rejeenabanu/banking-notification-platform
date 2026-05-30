package com.bank.transaction.mapper;

import com.bank.transaction.dto.TransactionResponse;
import com.bank.transaction.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionResponse toResponse(
            Transaction transaction) {

        return TransactionResponse.builder()
                .transactionId(
                        transaction.getTransactionId())
                .accountId(
                        transaction.getAccountId())
                .targetAccountId(
                        transaction.getTargetAccountId())
                .amount(
                        transaction.getAmount())
                .transactionType(
                        transaction.getTransactionType())
                .status(
                        transaction.getStatus())
                .description(
                        transaction.getDescription())
                .createdAt(
                        transaction.getCreatedAt())
                .build();
    }
}