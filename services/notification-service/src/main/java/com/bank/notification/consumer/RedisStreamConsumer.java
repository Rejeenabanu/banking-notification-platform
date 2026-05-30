package com.bank.notification.consumer;

import com.bank.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisStreamConsumer {

    private final StringRedisTemplate redisTemplate;
    private final NotificationService notificationService;

    private String lastId = "0";

    @Scheduled(fixedDelay = 3000)
    public void consumeEvents() {

        try {

            List<MapRecord<String, Object, Object>> records =
                    redisTemplate.opsForStream()
                            .range(
                                    "bank-events",
                                    org.springframework.data.domain.Range.unbounded()
                            );

            if(records == null || records.isEmpty()) {
                return;
            }

            for(MapRecord<String,Object,Object> record
                    : records) {

                String recordId =
                        record.getId().getValue();

                if(recordId.compareTo(lastId) <= 0) {
                    continue;
                }

                Object payload =
                        record.getValue()
                                .get("payload");

                notificationService.processEvent(
                        payload.toString());

                lastId = recordId;
            }

        } catch(Exception ex) {

            log.error(
                    "Error consuming events",
                    ex);
        }
    }
}