package com.example.todoapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity // Oznacza tabelę w bazie która odpowiada takiej klasie
@Table(name = "tasks")
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private int id;

    @Column(name = "description")
    @NotBlank(message = "Opis nie może być pusty")
    private String description;

    @Column(columnDefinition = "BOOLEAN")
    private boolean done;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdOn;

//    @Transient - oznacza że tego pola nie chcemy zapisywać do bazy danych, ale w request możemy tam wrzucic jakas wartosc
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private LocalDateTime updatedOn;

    public void updateFrom(final Task source) {
        description = source.description;
        done = source.done;
        deadline = source.deadline;
    }

    @PrePersist // Uruchomi się moment przed zapisaniem do bazy
    void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate // Uruchomi się przed update
    void preMerge() {
        updatedOn = LocalDateTime.now();
    }
}
