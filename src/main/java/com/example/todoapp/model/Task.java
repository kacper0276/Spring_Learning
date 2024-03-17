package com.example.todoapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity // Oznacza tabelę w bazie która odpowiada takiej klasie
@Table(name = "tasks")
public class Task {

}
