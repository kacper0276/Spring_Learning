package com.example.todoapp.adapter;

import com.example.todoapp.model.TaskGroup;
import com.example.todoapp.model.TaskGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlTaskGroupRepository extends TaskGroupRepository, JpaRepository<TaskGroup, Integer> {
    @Override
    @Query("SELECT DISTINCT g FROM TaskGroup g JOIN FETCH g.tasks t") // NativeQuery = false - zapytanie na encjach, NativeQuery = true - zapytanie na tabelach bazodanowych
    List<TaskGroup> findAll(); // Domyślnie inner join, jeśli grupa nie ma tasków to się nic nie zwróci

    @Override
    boolean existsByDoneIsFalseAndProject_Id(Integer projectId);
}
