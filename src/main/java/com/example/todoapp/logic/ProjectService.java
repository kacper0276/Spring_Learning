package com.example.todoapp.logic;

import com.example.todoapp.TaskConfigurationProperties;
import com.example.todoapp.model.*;
import com.example.todoapp.model.projection.GroupReadModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository repository;
    private final TaskGroupRepository taskGroupRepository;
    private final TaskConfigurationProperties config;

    public List<Project> readAll() {
        return repository.findAll();
    }

    public Project save(final Project toSave) {
        return repository.save(toSave);
    }

    public GroupReadModel createGroup(LocalDateTime deadline, int projectId) {
        if(!config.getTemplate().isAllowMultipleTasks() && taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
            throw new IllegalStateException("Only one undone group from project is allowed");
        }

        TaskGroup result = repository.findById(projectId)
                .map(project -> {
                   var targetGroup = new TaskGroup();
                    targetGroup.setDescription(project.getDescription());
                    targetGroup.setTasks(
                           project.getSteps().stream()
                           .map(step -> new Task(step.getDescription(), deadline.plusDays(step.getDaysToDeadline())))
                           .collect(Collectors.toSet())
                   );
                   return taskGroupRepository.save(targetGroup);
                }).orElseThrow(() -> new IllegalArgumentException("Project with given id not found"));

        return new GroupReadModel(result);
    }
}
