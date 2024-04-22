package com.example.todoapp.controller;

import com.example.todoapp.logic.TaskService;
import com.example.todoapp.model.Task;
import com.example.todoapp.model.TaskRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

//@RepositoryRestController
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final ApplicationEventPublisher eventPublisher;
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    // @Lazy - zaciąganie zalezności kiedy sa potrzebne
//  @Qualifier("sqlTaskRepository") - skad wstrzykujemy beana w konstruktorze
    public TaskController(ApplicationEventPublisher eventPublisher, final TaskRepository taskRepository, final TaskService taskService) {
        this.eventPublisher = eventPublisher;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "", params = {"!sort", "!page", "!size"}) // Pewność że nie zostały wykorzystane te parametry
    CompletableFuture<ResponseEntity<List<Task>>> readAllTasks() {
        logger.warn("Exposing all the tasks!");
        return taskService.findAllAsync().thenApply(ResponseEntity::ok);
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    ResponseEntity<List<Task>> readAllTasks(Pageable page) {
        logger.info("Custom pageable");
        return ResponseEntity.ok(taskRepository.findAll(page).getContent()); // Jak bez .getContent() to zwraca informację o paginacji (ile el na strone, która strona i zamiast List<Task> zwraca Page<Task>
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    ResponseEntity<Optional<Task>> readTaskById(@PathVariable int id) {
        return ResponseEntity.ok(taskRepository.findById(id));
    }

    @GetMapping("/search/done")
    ResponseEntity<List<Task>> readDoneTasks(@RequestParam(defaultValue = "true") boolean state) {
        return ResponseEntity.ok(taskRepository.findByDone(state));
    }

    @RequestMapping(method = RequestMethod.POST, path = "")
    ResponseEntity<Task> createTask(@RequestBody Task task) {
        logger.info("Create task");
        Task result = taskRepository.save(task);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody @Valid Task toUpdate) { // zamiast @Param można użyć @PathVariable("id") int id
        taskRepository.findById(id)
                .ifPresent(task -> {
                    task.updateFrom(toUpdate);
                    taskRepository.save(task);
                });
        return ResponseEntity.noContent().build();
    }

    @Transactional // Na początku transaction begin a na końcu transaction commit, jeżeli poleci exception to cała operacja się nie wykona, wszystkie zmiany wycofane, samo się zapisze, albo transactional albo repository.save() wszędzie
    @PatchMapping(path = "/{id}")
    ResponseEntity<?> toggleTask(@PathVariable int id) { // zamiast @Param można użyć @PathVariable("id") int id
        taskRepository.findById(id)
                .map(Task::toggle)
                .ifPresent(eventPublisher::publishEvent);
        return ResponseEntity.noContent().build();
    }
}
