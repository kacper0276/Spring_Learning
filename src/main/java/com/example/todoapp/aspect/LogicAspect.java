package com.example.todoapp.aspect;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogicAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogicAspect.class);
    private final Timer projectCreateGroupTimer;

    // MeterRegistry - rejestr metryk
    public LogicAspect(final MeterRegistry registry) {
        projectCreateGroupTimer = registry.timer("logic.product.create.group");
    }

    @Around("execution(* com.example.todoapp.logic.ProjectService.createGroup(..))")
    Object aroundProjectCreateGroup(ProceedingJoinPoint jp) { // ProceedingJoinPoint - punkt łączenia apsektu z logiką
        return projectCreateGroupTimer.record(() -> {
            try {
                return jp.proceed();
            } catch (Throwable e) {
                if (e instanceof RuntimeException) {
                    throw (RuntimeException) e;
                }
                throw new RuntimeException(e);
            }
        });
    }
}
