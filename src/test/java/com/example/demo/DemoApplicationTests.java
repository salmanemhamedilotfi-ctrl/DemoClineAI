package com.example.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DemoApplicationTests.class)
public class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private HelloController controller;

    @BeforeEach
    void setUp() {
        controller = new HelloController();
    }

    // ─── /api/hello ───────────────────────────────────────────

    @Test
    void helloWorld_shouldReturnHelloWorld() throws Exception {
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }

    // ─── /api/palindrome GET ──────────────────────────────────

    @Test
    void checkPalindromeGet_withPalindrome_shouldReturnTrue() throws Exception {
        mockMvc.perform(get("/api/palindrome").param("text", "racecar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("racecar"))
                .andExpect(jsonPath("$.palindrome").value(true));
    }

    @Test
    void checkPalindromeGet_withNonPalindrome_shouldReturnFalse() throws Exception {
        mockMvc.perform(get("/api/palindrome").param("text", "hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("hello"))
                .andExpect(jsonPath("$.palindrome").value(false));
    }

    @Test
    void checkPalindromeGet_withSpacesAndUppercase_shouldReturnTrue() throws Exception {
        mockMvc.perform(get("/api/palindrome").param("text", "A man a plan a canal Panama"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.palindrome").value(true));
    }

    // ─── /api/palindrome POST ─────────────────────────────────

    @Test
    void checkPalindrome_post_withPalindrome_shouldReturnTrue() throws Exception {
        mockMvc.perform(post("/api/palindrome")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("madam"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("madam"))
                .andExpect(jsonPath("$.palindrome").value(true));
    }

    @Test
    void checkPalindrome_post_withNonPalindrome_shouldReturnFalse() throws Exception {
        mockMvc.perform(post("/api/palindrome")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("world"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("world"))
                .andExpect(jsonPath("$.palindrome").value(false));
    }

    // ─── isPalindrome() unit tests ────────────────────────────

    @Test
    void isPalindrome_withNull_shouldReturnFalse() {
        assert !controller.isPalindrome(null);
    }

    @Test
    void isPalindrome_withEmptyString_shouldReturnTrue() {
        assert controller.isPalindrome("");
    }

    @Test
    void isPalindrome_withSingleChar_shouldReturnTrue() {
        assert controller.isPalindrome("a");
    }

    @Test
    void isPalindrome_withPalindrome_shouldReturnTrue() {
        assert controller.isPalindrome("racecar");
        assert controller.isPalindrome("madam");
        assert controller.isPalindrome("level");
    }

    @Test
    void isPalindrome_withNonPalindrome_shouldReturnFalse() {
        assert !controller.isPalindrome("hello");
        assert !controller.isPalindrome("world");
    }

    @Test
    void isPalindrome_withMixedCaseAndSpaces_shouldReturnTrue() {
        assert controller.isPalindrome("A man a plan a canal Panama");
        assert controller.isPalindrome("Was it a car or a cat I saw");
    }

    @Test
    void isPalindrome_withNumbers_shouldReturnTrue() {
        assert controller.isPalindrome("12321");
        assert !controller.isPalindrome("12345");
    }
}