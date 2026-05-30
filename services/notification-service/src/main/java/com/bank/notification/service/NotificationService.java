package com.bank.notification.service;

import com.bank.notification.entity.Notification;
import com.bank.notification.enums.NotificationStatus;
import com.bank.notification.enums.NotificationType;
import com.bank.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;

    private final EmailService emailService;

    public Notification processEvent(
            String payload) {

        Notification notification =
                Notification.builder()
                        .eventType(
                                "TRANSACTION_CREATED")
                        .payload(payload)
                        .notificationType(
                                NotificationType.EMAIL)
                        .status(
                                NotificationStatus.SENT)
                        .build();

        Notification saved =
                repository.save(notification);

        emailService.sendTransactionEmail(
                "customer@test.com",
                "Transaction Notification",
                buildEmailBody(payload));

        return saved;
    }

    private String buildEmailBody(
            String payload) {

        return """
                Dear Customer,

                A transaction has been processed.

                Transaction Details:

                %s

                Thank you,
                Banking Notification Platform
                """
                .formatted(payload);
    }
}