package com.example.demo.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    public enum Type { CHAT, JOIN, LEAVE }

    private Type type;
    private String sender;
    private String content;
}
