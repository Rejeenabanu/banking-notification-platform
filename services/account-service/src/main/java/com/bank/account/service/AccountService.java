package com.bank.account.service;

import com.bank.account.domain.AccountResponse;
import com.bank.account.domain.CreateAccountRequest;
import com.bank.account.domain.TransferRequest;
import com.bank.account.entity.Account;
import com.bank.account.enums.AccountStatus;
import com.bank.account.exception.AccountNotActiveException;
import com.bank.account.exception.InsufficientFundsException;
import com.bank.account.exception.ResourceNotFoundException;
import com.bank.account.mapper.AccountMapper;
import com.bank.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository repository;
    private final AccountMapper mapper;

    public AccountResponse createAccount(
            CreateAccountRequest request) {

        Account account =
                Account.builder()
                        .customerId(request.getCustomerId())
                        .accountType(request.getAccountType())
                        .balance(request.getInitialDeposit())
                        .status(AccountStatus.ACTIVE)
                        .accountNumber(generateAccountNumber())
                        .build();

        return mapper.toResponse(
                repository.save(account));
    }

    public AccountResponse getAccount(
            UUID accountId) {

        return mapper.toResponse(
                findAccount(accountId));
    }

    public List<AccountResponse> getAccountsByCustomer(
            UUID customerId) {

        return repository.findByCustomerId(customerId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public AccountResponse freezeAccount(
            UUID accountId) {

        Account account = findAccount(accountId);

        account.setStatus(AccountStatus.FROZEN);

        return mapper.toResponse(
                repository.save(account));
    }

    public AccountResponse closeAccount(
            UUID accountId) {

        Account account = findAccount(accountId);

        account.setStatus(AccountStatus.CLOSED);

        return mapper.toResponse(
                repository.save(account));
    }

    private Account findAccount(UUID id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Account not found"));
    }

    private String generateAccountNumber() {

        return "ACC" + System.currentTimeMillis();
    }

    @Transactional
    public AccountResponse deposit(
            UUID accountId,
            BigDecimal amount) {

        Account account = findAccount(accountId);

        validateAccount(account);

        account.setBalance(
                account.getBalance()
                        .add(amount));

        return mapper.toResponse(
                repository.save(account));
    }

    @Transactional
    public AccountResponse withdraw(
            UUID accountId,
            BigDecimal amount) {

        Account account = findAccount(accountId);

        validateAccount(account);

        if(account.getBalance()
                .compareTo(amount) < 0) {

            throw new InsufficientFundsException(
                    "Insufficient balance");
        }

        account.setBalance(
                account.getBalance()
                        .subtract(amount));

        return mapper.toResponse(
                repository.save(account));
    }

    @Transactional
    public void transfer(
            TransferRequest request) {

        Account fromAccount =
                findAccount(
                        request.getFromAccountId());

        Account toAccount =
                findAccount(
                        request.getToAccountId());

        validateAccount(fromAccount);
        validateAccount(toAccount);

        if(fromAccount.getBalance()
                .compareTo(request.getAmount()) < 0) {

            throw new InsufficientFundsException(
                    "Insufficient balance");
        }

        fromAccount.setBalance(
                fromAccount.getBalance()
                        .subtract(request.getAmount()));

        toAccount.setBalance(
                toAccount.getBalance()
                        .add(request.getAmount()));

        repository.save(fromAccount);
        repository.save(toAccount);
    }

    private void validateAccount(
            Account account) {

        if(account.getStatus()
                != AccountStatus.ACTIVE) {

            throw new AccountNotActiveException(
                    "Account is not active");
        }
    }
}