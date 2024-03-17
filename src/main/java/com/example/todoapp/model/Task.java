package com.example.todoapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity // Oznacza tabelę w bazie która odpowiada takiej klasie
@Table(name = "tasks")
@Getter
@Setter
public class Task {
    @Id
    private int id;

    @Column(name = "description")
    private String description;

    private boolean done;
}
