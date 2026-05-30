package com.bank.transaction.repository;

import com.bank.transaction.entity.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OutboxEventRepository
        extends JpaRepository<OutboxEvent, UUID> {

    List<OutboxEvent>
    findByProcessedFalseOrderByCreatedAtAsc();
}