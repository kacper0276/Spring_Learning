package com.example.todoapp.controller;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration")
class TaskControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository repository;

    @Test
    void httpGet_returnsGivenTask() throws Exception {
        // given
        int id = repository.save(new Task("foo", LocalDateTime.now())).getId();

        // when + then
        mockMvc.perform(get("/tasks/" + id))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void httpGet_returnsTasksList() throws Exception {
        // given

        // when + then
        mockMvc.perform(get("/tasks"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void httpPost_createNewTask() throws Exception {
        // given
        int sizeTasksList = repository.findAll().size();
        int id = repository.save(new Task("Testowy", LocalDateTime.now())).getId();

        // when + then
        mockMvc.perform(get("/tasks"))
                .andExpect(jsonPath("$.*", hasSize(sizeTasksList + 1)));
    }
}


