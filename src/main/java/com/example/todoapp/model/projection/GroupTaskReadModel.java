package com.example.todoapp.model.projection;

import com.example.todoapp.model.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupTaskReadModel {
    private String description;
    private boolean done;

    GroupTaskReadModel(Task source) {
        description = source.getDescription();
        done = source.isDone();
    }
}
