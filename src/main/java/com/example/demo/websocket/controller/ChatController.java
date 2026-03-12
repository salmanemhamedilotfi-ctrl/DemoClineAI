package com.example.demo.websocket.controller;

import com.example.demo.websocket.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

/**
 * STOMP chat controller.
 * Clients subscribe to /topic/chat and send messages to /app/chat.send or /app/chat.join.
 */
@Controller
public class ChatController {

    @MessageMapping("/chat.send")
    @SendTo("/topic/chat")
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        return message;
    }

    @MessageMapping("/chat.join")
    @SendTo("/topic/chat")
    public ChatMessage addUser(@Payload ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        message.setType(ChatMessage.Type.JOIN);
        message.setContent(message.getSender() + " joined the chat!");
        return message;
    }
}
