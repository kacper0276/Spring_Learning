package com.example.todoapp.logic;

import com.example.todoapp.TaskConfigurationProperties;
import com.example.todoapp.model.ProjectRepository;
import com.example.todoapp.model.TaskGroupRepository;
import com.example.todoapp.model.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogicConfiguration {
    @Bean // Rejestracja Beanem ProjectService
    ProjectService projectService(final ProjectRepository respository,
                                  final TaskGroupRepository taskGroupRepository,
                                  final TaskConfigurationProperties config,
                                  final TaskGroupService taskGroupService
                                  ) {
        return new ProjectService(respository, taskGroupRepository, config, taskGroupService);
    }

    @Bean
    TaskGroupService taskGroupService(final TaskGroupRepository taskGroupRepository, final TaskRepository taskRepository) {
        return new TaskGroupService(taskGroupRepository, taskRepository);
    }
}
