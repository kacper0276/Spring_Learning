package com.example.todoapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // Oznacza tabelę w bazie która odpowiada takiej klasie
@Table(name = "tasks")
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;

    private boolean done;
}
