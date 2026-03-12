package com.example.demo.websocket.controller;

import com.example.demo.websocket.model.Notification;
import com.example.demo.websocket.service.NotificationService;
import org.springframework.web.bind.annotation.*;

/**
 * REST endpoint used to trigger a push notification to all WebSocket subscribers.
 * POST /api/notifications  { "title": "...", "body": "...", "level": "info" }
 */
@RestController
@RequestMapping("/api/notifications")
public class NotificationRestController {

    private final NotificationService notificationService;

    public NotificationRestController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public Notification push(@RequestBody Notification notification) {
        notificationService.sendNotification(notification);
        return notification;
    }

    // Convenience shortcuts
    @GetMapping("/demo/info")
    public Notification demoInfo() {
        Notification n = new Notification("Info", "This is an informational notification.", "info");
        notificationService.sendNotification(n);
        return n;
    }

    @GetMapping("/demo/success")
    public Notification demoSuccess() {
        Notification n = new Notification("Success", "Operation completed successfully!", "success");
        notificationService.sendNotification(n);
        return n;
    }

    @GetMapping("/demo/warning")
    public Notification demoWarning() {
        Notification n = new Notification("Warning", "Please review your configuration.", "warning");
        notificationService.sendNotification(n);
        return n;
    }

    @GetMapping("/demo/error")
    public Notification demoError() {
        Notification n = new Notification("Error", "Something went wrong on the server.", "error");
        notificationService.sendNotification(n);
        return n;
    }
}
