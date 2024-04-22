package com.example.todoapp.reports;

import com.example.todoapp.model.event.TaskEvent;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "task_events")
@NoArgsConstructor
class PersistedTaskEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int taskId;
    LocalDateTime occurrence;
    String name;

    PersistedTaskEvent(TaskEvent source) {
        taskId = source.getTaskId();
        name = source.getClass().getSimpleName();
        occurrence = LocalDateTime.ofInstant(source.getOccurrence(), ZoneId.systemDefault());
    }
}
