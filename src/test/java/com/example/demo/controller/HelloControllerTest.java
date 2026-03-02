package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testHelloWorld() {
        webTestClient.get()
            .uri("/api/hello")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class).isEqualTo("Hello World!");
    }

    @Test
    public void testPalindromeGet() {
        webTestClient.get()
            .uri("/api/palindrome?text=racecar")
            .exchange()
            .expectStatus().isOk()
            .expectBody(HelloController.PalindromeResult.class)
            .value(result -> {
                assertEquals("racecar", result.getText());
                assertTrue(result.isPalindrome());
            });
    }

    @Test
    public void testPalindromeGetNotPalindrome() {
        webTestClient.get()
            .uri("/api/palindrome?text=hello")
            .exchange()
            .expectStatus().isOk()
            .expectBody(HelloController.PalindromeResult.class)
            .value(result -> {
                assertEquals("hello", result.getText());
                assertFalse(result.isPalindrome());
            });
    }

    @Test
    public void testPalindromeGetWithSpaces() {
        webTestClient.get()
            .uri("/api/palindrome?text=A%20man%20a%20plan%20a%20canal%20Panama")
            .exchange()
            .expectStatus().isOk()
            .expectBody(HelloController.PalindromeResult.class)
            .value(result -> {
                assertEquals("A man a plan a canal Panama", result.getText());
                assertTrue(result.isPalindrome());
            });
    }

    @Test
    public void testPalindromeGetWithPunctuation() {
        webTestClient.get()
            .uri("/api/palindrome?text=A%20man,%20a%20plan,%20a%20canal:%20Panama!")
            .exchange()
            .expectStatus().isOk()
            .expectBody(HelloController.PalindromeResult.class)
            .value(result -> {
                assertEquals("A man, a plan, a canal: Panama!", result.getText());
                assertTrue(result.isPalindrome());
            });
    }

    @Test
    public void testPalindromePost() {
        webTestClient.post()
            .uri("/api/palindrome")
            .bodyValue("racecar")
            .exchange()
            .expectStatus().isOk()
            .expectBody(HelloController.PalindromeResult.class)
            .value(result -> {
                assertEquals("racecar", result.getText());
                assertTrue(result.isPalindrome());
            });
    }

    @Test
    public void testPalindromePostNotPalindrome() {
        webTestClient.post()
            .uri("/api/palindrome")
            .bodyValue("hello")
            .exchange()
            .expectStatus().isOk()
            .expectBody(HelloController.PalindromeResult.class)
            .value(result -> {
                assertEquals("hello", result.getText());
                assertFalse(result.isPalindrome());
            });
    }

    @Test
    public void testPalindromePostWithSpaces() {
        webTestClient.post()
            .uri("/api/palindrome")
            .bodyValue("A man a plan a canal Panama")
            .exchange()
            .expectStatus().isOk()
            .expectBody(HelloController.PalindromeResult.class)
            .value(result -> {
                assertEquals("A man a plan a canal Panama", result.getText());
                assertTrue(result.isPalindrome());
            });
    }

    @Test
    public void testPalindromePostEmptyString() {
        webTestClient.post()
            .uri("/api/palindrome")
            .bodyValue("")
            .exchange()
            .expectStatus().isOk()
            .expectBody(HelloController.PalindromeResult.class)
            .value(result -> {
                assertEquals("", result.getText());
                assertTrue(result.isPalindrome());
            });
    }

    @Test
    public void testPalindromePostSingleCharacter() {
        webTestClient.post()
            .uri("/api/palindrome")
            .bodyValue("a")
            .exchange()
            .expectStatus().isOk()
            .expectBody(HelloController.PalindromeResult.class)
            .value(result -> {
                assertEquals("a", result.getText());
                assertTrue(result.isPalindrome());
            });
    }

    @Test
    public void testPalindromePostNumbers() {
        webTestClient.post()
            .uri("/api/palindrome")
            .bodyValue("12321")
            .exchange()
            .expectStatus().isOk()
            .expectBody(HelloController.PalindromeResult.class)
            .value(result -> {
                assertEquals("12321", result.getText());
                assertTrue(result.isPalindrome());
            });
    }
}