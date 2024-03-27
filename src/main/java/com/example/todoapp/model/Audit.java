package com.example.todoapp.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//@MappedSuperclass // Oznacza że to klasa bazowa
@Embeddable // Oznacza że ta klasa jest do osadzenie w innym miejscu
public class Audit {
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
