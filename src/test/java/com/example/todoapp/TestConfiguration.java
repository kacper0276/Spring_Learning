package com.example.todoapp;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.TaskGroup;
import com.example.todoapp.model.TaskRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.*;

@Configuration
public class TestConfiguration {
    @Bean
    @Primary
    @Profile("!integration")
    DataSource e2eTestDataSource() {
        var result = new DriverManagerDataSource("jdbc:mysql://localhost:3306/spring", "root", "");
        result.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return result;
    }

    @Bean
//    @ConditionalOnMissingBean // Te definicje metod obowiazuja gdy nie ma zdefiniowanego innego TaskRepository
//    @ConditionalOnBean() Jesli jakis bean istnieje to ten tez
    @Primary // Wiekszy priorytet
    @Profile({"integration", "!prod", "!local"}) // Gdy jest profil taki to to obowiazuje
    TaskRepository testRepo() {
        return new TaskRepository(){
            private Map<Integer, Task> tasks = new HashMap<>();

            @Override
            public List<Task> findAll() {
                return new ArrayList<>(tasks.values());
            }

            @Override
            public Page<Task> findAll(Pageable page) {
                return null;
            }

            @Override
            public Optional<Task> findById(Integer i) {
                return Optional.ofNullable(tasks.get(i));
            }

            @Override
            public Task save(Task entity) {
                int key = tasks.size() + 1;
                try {
                    var field = Task.class.getDeclaredField("id");
                    field.setAccessible(true);
                    field.set(entity, key);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                tasks.put(key, entity);
                return tasks.get(key);
            }

            @Override
            public boolean existsById(Integer id) {
                return tasks.containsKey(id);
            }

            @Override
            public boolean existsByDoneIsFalseAndGroup_Id(Integer groupId) {
                return false;
            }

            @Override
            public List<Task> findByDoneIsTrue() {
                return null;
            }

            @Override
            public List<Task> findByDone(boolean done) {
                return List.of();
            }

            @Override
            public List<Task> findAllByGroup_Id(Integer groupId) {
                return List.of();
            }
        };
    }
}
