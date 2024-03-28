package com.example.todoapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "task_groups")
@Getter
@Setter
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private int id;

    @NotBlank(message = "Opis nie może być pusty")
    private String description;
    private boolean done;

    @Embedded
    private Audit audit = new Audit();
}
