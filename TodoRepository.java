package com.example.taskscheduler.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.taskscheduler.entity.Todo;

public interface TodoRepository extends CrudRepository<Todo, Integer> {
}
