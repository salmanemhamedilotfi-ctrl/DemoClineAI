package com.example.demo.websocket.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

/**
 * Raw WebSocket handler – echoes every message back to the sender
 * and broadcasts a join/leave notification to all other connected clients.
 */
@Component
public class EchoWebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        broadcast("[Server] Client " + session.getId() + " joined. Total connected: " + sessions.size(), session);
        session.sendMessage(new TextMessage("[Server] Welcome! You are connected. Session id: " + session.getId()));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        // Echo back to sender
        session.sendMessage(new TextMessage("[Echo] " + payload));
        // Broadcast to others
        broadcast("[Client " + session.getId().substring(0, 6) + "] " + payload, session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        broadcast("[Server] Client " + session.getId() + " left. Total connected: " + sessions.size(), null);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        session.close(CloseStatus.SERVER_ERROR);
        sessions.remove(session);
    }

    private void broadcast(String message, WebSocketSession exclude) {
        sessions.forEach(s -> {
            if (s.isOpen() && !s.equals(exclude)) {
                try {
                    s.sendMessage(new TextMessage(message));
                } catch (Exception ignored) {}
            }
        });
    }
}
