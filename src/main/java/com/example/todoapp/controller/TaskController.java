package com.example.todoapp.controller;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.TaskRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RepositoryRestController
@AllArgsConstructor
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository taskRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/tasks", params = {"!sort", "!page", "!size"}) // Pewność że nie zostały wykorzystane te parametry
    ResponseEntity<List<Task>> readAllTasks() {
        logger.warn("Exposing all the tasks!");
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/tasks")
    ResponseEntity<List<Task>> readAllTasks(Pageable page) {
        logger.info("Custom pageable");
        return ResponseEntity.ok(taskRepository.findAll(page).getContent()); // Jak bez .getContent() to zwraca informację o paginacji (ile el na strone, która strona i zamiast List<Task> zwraca Page<Task>
    }
}
