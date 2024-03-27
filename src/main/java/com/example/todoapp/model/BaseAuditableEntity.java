package com.example.todoapp.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass // Oznacza że to klasa bazowa
public abstract class BaseAuditableEntity {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdOn;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private LocalDateTime updatedOn;

    @PrePersist
        // Uruchomi się moment przed zapisaniem do bazy
    void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
        // Uruchomi się przed update
    void preMerge() {
        updatedOn = LocalDateTime.now();
    }
}
