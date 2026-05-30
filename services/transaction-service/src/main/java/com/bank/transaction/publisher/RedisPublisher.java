package com.bank.transaction.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RedisPublisher {

    private final StringRedisTemplate redisTemplate;

    public void publish(
            String payload) {

        redisTemplate.opsForStream()
                .add(
                        "bank-events",
                        Map.of(
                                "payload",
                                payload
                        )
                );
    }
}