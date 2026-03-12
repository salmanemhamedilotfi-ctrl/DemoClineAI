package com.example.demo.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/websocket")
public class WebSocketDemoController {

    @GetMapping
    public String index() {
        return "websocket/index";
    }

    @GetMapping("/echo")
    public String echo() {
        return "websocket/echo";
    }

    @GetMapping("/chat")
    public String chat() {
        return "websocket/chat";
    }

    @GetMapping("/notifications")
    public String notifications() {
        return "websocket/notifications";
    }
}
