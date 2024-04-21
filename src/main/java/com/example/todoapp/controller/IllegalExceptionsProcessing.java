package com.example.todoapp.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE}) // Do czego możemy użyć tej adnotacji
@Retention(RetentionPolicy.RUNTIME) // Jak długo ta adnotacja ma zostawać
public @interface IllegalExceptionsProcessing {
}
