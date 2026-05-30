package com.bank.transaction.scheduler;

import com.bank.transaction.entity.OutboxEvent;
import com.bank.transaction.publisher.RedisPublisher;
import com.bank.transaction.repository.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OutboxScheduler {

    private final OutboxEventRepository repository;
    private final RedisPublisher publisher;

    @Scheduled(fixedDelay = 5000)
    public void publishEvents() {

        List<OutboxEvent> events =
                repository
                        .findByProcessedFalseOrderByCreatedAtAsc();

        for (OutboxEvent event : events) {

            publisher.publish(
                    event.getPayload());

            event.setProcessed(true);

            repository.save(event);
        }
    }
}