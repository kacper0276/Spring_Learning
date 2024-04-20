package com.example.todoapp.model.projection;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.TaskGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GroupTaskWriteModel {
    private String description;
    private LocalDateTime deadline;

    Task toTask(final TaskGroup taskGroup) {
        return new Task(description, deadline, taskGroup);
    }
}
