package com.example.todoapp.controller;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerE2ETest {
    @LocalServerPort // Wstrzykiwanie portu ktory sie wylosowal
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    TaskRepository repository;

    @Test
    void httpGet_returnsAllTasks() {
        // given
        int initial = repository.findAll().size();
        repository.save(new Task("foo", LocalDateTime.now()));
        repository.save(new Task("bar", LocalDateTime.now()));

        // when
        Task[] result = restTemplate.getForObject("http://localhost:" + port + "/tasks", Task[].class);

        // then
        assertThat(result).hasSize(initial + 2);
    }

    @Test
    void httpGet_returnsGivenTask() {
        // given
        Task task = repository.save(new Task("Test task", LocalDateTime.now()));

        // when
        Task result = restTemplate.getForObject("http://localhost:" + port + "/tasks/" + task.getId(), Task.class);

        // then
        assertThat(result.getDescription()).isEqualTo(task.getDescription());
        assertThat(result.getDeadline()).isEqualTo(task.getDeadline());
        assertThat(result.getId()).isEqualTo(task.getId());
    }
}