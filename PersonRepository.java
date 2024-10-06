package com.example.taskscheduler.repository;

import com.example.taskscheduler.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
