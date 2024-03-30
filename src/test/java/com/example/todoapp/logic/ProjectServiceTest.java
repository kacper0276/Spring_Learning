package com.example.todoapp.logic;

import com.example.todoapp.model.TaskGroup;
import com.example.todoapp.model.TaskGroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceTest {

    @Test
    @DisplayName("should throw IllegalStateException when configured to allow just 1 group and other undone group exist")
    void createGroup_noMultipleGroupsConfig_And_openGroupExists_throwsIllegalStateException() {
        // given - miejsce gdzie przygotowujemy dane, mock - wydmuszka
        var mockGroupRepository = new TaskGroupRepository() { // Klasa anonimowa
            @Override
            public List<TaskGroup> findAll() {
                return null;
            }

            @Override
            public Optional<TaskGroup> findById(Integer id) {
                return Optional.empty();
            }

            @Override
            public TaskGroup save(TaskGroup entity) {
                return null;
            }

            @Override
            public boolean existsByDoneIsFalseAndProject_Id(Integer projectId) {
                return false;
            }

        };
        // when - wołamy testowaną metode

        // then - sprawdzamy czy metoda daje wynik taki jaki chcemy
    }
}