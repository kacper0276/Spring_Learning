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
public class Task extends BaseAuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private int id;

    @Column(name = "description")
    @NotBlank(message = "Opis nie może być pusty")
    private String description;

    // @Transient - oznacza że tego pola nie chcemy zapisywać do bazy danych, ale w request możemy tam wrzucic jakas wartosc
    @Column(columnDefinition = "BOOLEAN")
    private boolean done;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    public void updateFrom(final Task source) {
        description = source.description;
        done = source.done;
        deadline = source.deadline;
    }
}
