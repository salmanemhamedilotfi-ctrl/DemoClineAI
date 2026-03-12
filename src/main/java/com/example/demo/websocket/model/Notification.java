package com.example.demo.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private String title;
    private String body;
    private String level;   // info | success | warning | error
    private Instant timestamp = Instant.now();

    public Notification(String title, String body, String level) {
        this.title = title;
        this.body = body;
        this.level = level;
    }
}
