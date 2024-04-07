package com.example.todoapp.controller;

import jakarta.servlet.*;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggerFilter implements Filter, Ordered {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    // Ustalanie priorytetu, jeśli nasz filtr ma return 1 a inny return 2 to wyzszy priorytet ma ten z 2
    @Override
    public int getOrder() {
        return 2;
    }
}
