package com.bank.notification.controller;

import com.bank.notification.entity.Notification;
import com.bank.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationRepository repository;

    @GetMapping
    public List<Notification> getAll() {

        return repository.findAll();
    }
}