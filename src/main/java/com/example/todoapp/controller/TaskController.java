package com.example.todoapp.controller;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.TaskRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RepositoryRestController
@RestController
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

    @RequestMapping(method = RequestMethod.PUT, path = "/tasks/{id}")
    ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody @Valid Task toUpdate) { // zamiast @Param można użyć @PathVariable("id") int id
        if(!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        taskRepository.findById(id)
                .ifPresent(task -> {
                    task.updateFrom(toUpdate);
                    taskRepository.save(task);
                });
        return ResponseEntity.noContent().build();
    }

    @Transactional // Na początku transaction begin a na końcu transaction commit, jeżeli poleci exception to cała operacja się nie wykona, wszystkie zmiany wycofane, samo się zapisze, albo transactional albo repository.save() wszędzie
    @PatchMapping(path = "/tasks/{id}")
    ResponseEntity<?> toggleTask(@PathVariable int id) { // zamiast @Param można użyć @PathVariable("id") int id
        if(!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        taskRepository.findById(id)
                .ifPresent(task -> task.setDone(!task.isDone()));
        return ResponseEntity.noContent().build();
    }
}
