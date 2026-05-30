package com.bank.transaction.service;

import com.bank.transaction.entity.OutboxEvent;
import com.bank.transaction.repository.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutboxService {

    private final OutboxEventRepository repository;

    public OutboxEvent save(
            OutboxEvent event) {

        return repository.save(event);
    }
}