package com.bank.account.mapper;

import com.bank.account.domain.AccountResponse;
import com.bank.account.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponse toResponse(
            Account account) {

        return AccountResponse.builder()
                .accountId(account.getAccountId())
                .customerId(account.getCustomerId())
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .status(account.getStatus())
                .balance(account.getBalance())
                .createdAt(account.getCreatedAt())
                .build();
    }
}
