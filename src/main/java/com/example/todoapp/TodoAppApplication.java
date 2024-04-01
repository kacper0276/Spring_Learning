package com.example.todoapp;

import com.example.todoapp.model.TaskRepository;
import jakarta.validation.Validator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class TodoAppApplication{

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

	@Bean // Spring cos zaczytal do kontektsu i traktowal jako singleton
	Validator validator(TaskRepository repository) {
		return repository.findById(1)
				.map((task) -> new LocalValidatorFactoryBean())
				.orElse(null);
//		return new LocalValidatorFactoryBean();
	}
	
}
