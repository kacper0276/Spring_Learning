package com.example.todoapp.logic;

import com.example.todoapp.TaskConfigurationProperties;
import com.example.todoapp.model.ProjectRepository;
import com.example.todoapp.model.TaskGroupRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:applicationContext.xml") // classpath - resources
public class LogicConfiguration {
//    @Bean // Rejestracja Beanem ProjectService
//    ProjectService service(ProjectRepository respository, TaskGroupRepository taskGroupRepository, TaskConfigurationProperties config) {
//        return new ProjectService(respository, taskGroupRepository, config);
//    }
}
