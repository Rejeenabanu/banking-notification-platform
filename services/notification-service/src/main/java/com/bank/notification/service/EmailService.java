package com.bank.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendTransactionEmail(
            String recipient,
            String subject,
            String body) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setFrom(
                "banking-platform@local.dev");

        message.setTo(recipient);

        message.setSubject(subject);

        message.setText(body);

        mailSender.send(message);

        log.info(
                "Email sent successfully to {}",
                recipient);
    }
}