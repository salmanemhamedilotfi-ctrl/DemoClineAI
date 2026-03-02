package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Hello API", description = "API for hello world and palindrome operations")
public class HelloController {

    @Operation(
        summary = "Get hello world message",
        description = "Returns a simple hello world message",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
        }
    )
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @Operation(
        summary = "Check if text is a palindrome (POST)",
        description = "Checks if the provided text is a palindrome",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Text to check",
            required = true,
            content = @Content(schema = @Schema(type = "string"))
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
        }
    )
    @PostMapping("/palindrome")
    public PalindromeResult checkPalindrome(@RequestBody String text) {
        boolean isPalindrome = isPalindrome(text);
        return new PalindromeResult(text, isPalindrome);
    }

    @Operation(
        summary = "Check if text is a palindrome (GET)",
        description = "Checks if the provided text is a palindrome using query parameter",
        parameters = {
            @Parameter(name = "text", description = "Text to check", required = true)
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
        }
    )
    @GetMapping("/palindrome")
    public PalindromeResult checkPalindromeGet(@RequestParam String text) {
        boolean isPalindrome = isPalindrome(text);
        return new PalindromeResult(text, isPalindrome);
    }

    public boolean isPalindrome(String text) {
        if (text == null) {
            return false;
        }
        
        // Normalize the text: remove spaces, convert to lowercase
        String normalized = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        
        // Check if the normalized text reads the same forwards and backwards
        int left = 0;
        int right = normalized.length() - 1;
        
        while (left < right) {
            if (normalized.charAt(left) != normalized.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }

    public static class PalindromeResult {
        private String text;
        private boolean isPalindrome;

        public PalindromeResult(String text, boolean isPalindrome) {
            this.text = text;
            this.isPalindrome = isPalindrome;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isPalindrome() {
            return isPalindrome;
        }

        public void setPalindrome(boolean palindrome) {
            isPalindrome = palindrome;
        }
    }
}