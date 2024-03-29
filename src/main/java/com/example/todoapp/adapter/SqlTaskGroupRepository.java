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
    @Query("select * from TaskGroup g join fetch g.tasks") // NativeQuery = false - zapytanie na encjach, NativeQuery = true - zapytanie na tabelach bazodanowych
    List<TaskGroup> findAll();
}
