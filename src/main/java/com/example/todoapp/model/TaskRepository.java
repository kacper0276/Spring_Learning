package com.example.todoapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource // (path = "todos", collectionResourceRel = "todos") collectionResourceRel - jak powinien być opisywany w kolekcji
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Task entity);

    @RestResource(path = "done", rel = "done")
    List<Task> findByDoneIsTrue(); // LUB List<Task> findByDone(@Param("state") boolean done);
}
