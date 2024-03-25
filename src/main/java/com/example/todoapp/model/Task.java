package com.example.todoapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private int id;

    @Column(name = "description")
    @NotBlank(message = "Opis nie może być pusty")
    private String description;

    private boolean done;

    @Column(name = "deadline")
    private LocalDateTime deadline;
}
