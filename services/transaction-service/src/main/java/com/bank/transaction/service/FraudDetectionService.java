package com.bank.transaction.service;

import com.bank.transaction.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class FraudDetectionService {

    private static final BigDecimal LIMIT =
            BigDecimal.valueOf(100000);

    public boolean isFraud(
            Transaction transaction) {

        if(transaction.getAmount()
                .compareTo(LIMIT) > 0) {

            log.warn(
                    "Potential fraud detected {}",
                    transaction.getTransactionId());

            return true;
        }

        return false;
    }
}