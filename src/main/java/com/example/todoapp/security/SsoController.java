package com.example.todoapp.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SsoController {
    @GetMapping("/logout")
    String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "index";
    }
}
