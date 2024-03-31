package com.example.todoapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Entity
@Table(name = "task_groups")
@Getter
@Setter
@NoArgsConstructor
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    @NotBlank(message = "Opis nie może być pusty")
    private String description;
    private boolean done;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "group") // FetchType.Eager - każdorazowo jak zaciągamy grupę to też Taski, Lazy - nie, Cascade - jeśli coś robimy w grupie to taskom też
    private Set<Task> tasks;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
