package com.example.demo.websocket.config;

import com.example.demo.websocket.handler.EchoWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketConfigurer, WebSocketMessageBrokerConfigurer {

    private final EchoWebSocketHandler echoWebSocketHandler;

    public WebSocketConfig(EchoWebSocketHandler echoWebSocketHandler) {
        this.echoWebSocketHandler = echoWebSocketHandler;
    }

    // ── Raw WebSocket ──────────────────────────────────────────────────────────
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoWebSocketHandler, "/ws/echo")
                .setAllowedOriginPatterns("*");
    }

    // ── STOMP / SockJS ─────────────────────────────────────────────────────────
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Client subscribes to topics under /topic
        registry.enableSimpleBroker("/topic");
        // Client sends messages to destinations prefixed with /app
        registry.setApplicationDestinationPrefixes("/app");
    }
}
