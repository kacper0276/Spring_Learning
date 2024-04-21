package com.example.todoapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogicAspect {
    @Around()
    Object aroundProjectCreateGroup(ProceedingJoinPoint jp) { // ProceedingJoinPoint - punkt łączenia apsektu z logiką
        return jp.proceed();
    }
}
