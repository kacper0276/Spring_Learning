package com.example.todoapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface SqlTaskRepository extends TaskRepository, JpaRepository<Task, Integer> {
    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from tasks where id=?1") // ?1 - pierwszy argument z tej metody
    boolean existsById(Integer id);

    @Override
    @Query(nativeQuery = true, value = "select * from tasks where id=:id")
    Optional<Task> findById(@Param("id") Integer i);
}
