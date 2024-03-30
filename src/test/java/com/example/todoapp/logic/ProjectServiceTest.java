package com.example.todoapp.logic;


import com.example.todoapp.TaskConfigurationProperties;
import com.example.todoapp.model.TaskGroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectServiceTest {

    @Test
    @DisplayName("should throw IllegalStateException when configured to allow just 1 group and other undone group exist")
    void createGroup_noMultipleGroupsConfig_And_openGroupExists_throwsIllegalStateException() {
        // given - miejsce gdzie przygotowujemy dane, mock - wydmuszka
        var mockGroupRepository = mock(TaskGroupRepository.class); // Jaką klase chcemy zamockowac
        when(mockGroupRepository.existsByDoneIsFalseAndProject_Id(anyInt())).thenReturn(true);
        // and
        var mockTemplate = mock(TaskConfigurationProperties.Template.class);
        when(mockTemplate.isAllowMultipleTasks()).thenReturn(false);
        // and
        var mockConfig = mock(TaskConfigurationProperties.class);
        when(mockConfig.getTemplate()).thenReturn(mockTemplate);
        // system under test
        var toTest = new ProjectService(null, mockGroupRepository, mockConfig);

        // when - wołamy testowaną metode
        var exception = catchThrowable(() -> toTest.createGroup(LocalDateTime.now(), 0)); // Przechwytuje wyjątek

        // then - sprawdzamy czy metoda daje wynik taki jaki chcemy
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("one undone group"); // Czy message exceptiona zawiera treść
    }
}