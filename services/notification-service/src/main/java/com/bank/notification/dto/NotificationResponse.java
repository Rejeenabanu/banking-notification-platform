package com.bank.notification.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class NotificationResponse {

    private UUID notificationId;

    private String eventType;

    private String payload;

    private String status;

    private LocalDateTime createdAt;
}