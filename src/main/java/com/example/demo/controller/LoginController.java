package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Tag(name = "Login API", description = "Login and dashboard operations")
public class LoginController {

    @Operation(
        summary = "Show login form",
        description = "Displays the login form. Accepts an optional error parameter to show error messages."
    )
    @GetMapping("/login")
    public String showLoginForm(
        @Parameter(description = "Error flag to show invalid credentials message") 
        @RequestParam(value = "error", required = false) String error, 
        Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        return "login";
    }

    @Operation(
        summary = "Process login",
        description = "Processes login credentials and redirects to dashboard or login with error."
    )
    @PostMapping("/login")
    public String processLogin(
        @Parameter(description = "Username for authentication", required = true)
        @RequestParam String username, 
        @Parameter(description = "Password for authentication", required = true)
        @RequestParam String password) {
        
        // Simple validation - in a real app, you'd check against a database
        if ("admin".equals(username) && "password".equals(password)) {
            return "redirect:/dashboard";
        }
        
        return "redirect:/login?error=true";
    }

    @Operation(
        summary = "Show dashboard",
        description = "Displays the dashboard page for authenticated users."
    )
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }

    @Operation(
        summary = "Home redirect",
        description = "Redirects to login page as the home page."
    )
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
}