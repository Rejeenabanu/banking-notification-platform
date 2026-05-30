package com.bank.transaction.service;

import com.bank.transaction.client.AccountServiceClient;
import com.bank.transaction.dto.*;
import com.bank.transaction.entity.*;
import com.bank.transaction.enums.EventType;
import com.bank.transaction.enums.TransactionStatus;
import com.bank.transaction.enums.TransactionType;
import com.bank.transaction.mapper.TransactionMapper;
import com.bank.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService {

    private final TransactionRepository repository;
    private final OutboxService outboxService;
    private final TransactionMapper mapper;
    private final AccountServiceClient accountClient;
    private final ObjectMapper objectMapper;
    private final FraudDetectionService fraudService;

    public TransactionResponse deposit(
            DepositRequest request)
            throws Exception {

        accountClient.deposit(
                request.getAccountId(),
                request);

        Transaction tx =
                Transaction.builder()
                        .accountId(
                                request.getAccountId())
                        .amount(
                                request.getAmount())
                        .transactionType(
                                TransactionType.DEPOSIT)
                        .status(
                                TransactionStatus.COMPLETED)
                        .description(
                                "Deposit Transaction")
                        .build();

        Transaction saved =
                repository.save(tx);

        saveOutbox(saved);

        return mapper.toResponse(saved);
    }

    private void saveOutbox(
            Transaction transaction)
            throws Exception {

        OutboxEvent event =
                OutboxEvent.builder()
                        .aggregateType(
                                "TRANSACTION")
                        .aggregateId(
                                transaction
                                        .getTransactionId()
                                        .toString())
                        .eventType(
                                EventType
                                        .TRANSACTION_CREATED
                                        .name())
                        .payload(
                                objectMapper
                                        .writeValueAsString(
                                                transaction))
                        .processed(false)
                        .build();

        outboxService.save(event);
    }

    public TransactionResponse withdraw(
            WithdrawRequest request)
            throws Exception {

        accountClient.withdraw(
                request.getAccountId(),
                request);

        Transaction tx =
                Transaction.builder()
                        .accountId(
                                request.getAccountId())
                        .amount(
                                request.getAmount())
                        .transactionType(
                                TransactionType.WITHDRAW)
                        .status(
                                TransactionStatus.COMPLETED)
                        .description(
                                "Withdraw Transaction")
                        .build();

        Transaction saved =
                repository.save(tx);

        saveOutbox(saved);

        fraudDetection(saved);

        return mapper.toResponse(saved);
    }

    public TransactionResponse transfer(
            TransferRequest request)
            throws Exception {

        accountClient.transfer(request);

        Transaction tx =
                Transaction.builder()
                        .accountId(
                                request.getFromAccountId())
                        .targetAccountId(
                                request.getToAccountId())
                        .amount(
                                request.getAmount())
                        .transactionType(
                                TransactionType.TRANSFER)
                        .status(
                                TransactionStatus.COMPLETED)
                        .description(
                                "Transfer Transaction")
                        .build();

        Transaction saved =
                repository.save(tx);

        saveOutbox(saved);

        fraudDetection(saved);

        return mapper.toResponse(saved);
    }

    private void fraudDetection(
            Transaction transaction)
            throws Exception {

        if(fraudService.isFraud(transaction)) {

            OutboxEvent event =
                    OutboxEvent.builder()
                            .aggregateType(
                                    "TRANSACTION")
                            .aggregateId(
                                    transaction
                                            .getTransactionId()
                                            .toString())
                            .eventType(
                                    EventType.FRAUD_ALERT
                                            .name())
                            .payload(
                                    objectMapper
                                            .writeValueAsString(
                                                    transaction))
                            .processed(false)
                            .build();

            outboxService.save(event);
        }
    }
}