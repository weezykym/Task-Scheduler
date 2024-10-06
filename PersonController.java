package com.example.taskscheduler.controller;

import com.example.taskscheduler.entity.Person;
import com.example.taskscheduler.entity.Todo;
import com.example.taskscheduler.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class PersonController {
    @Autowired
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    //Get all people URL
    @GetMapping("/person")
    public Iterable<Person> getAllPeople() {
        System.out.printf("Getting all people in the database: \n ");
        return this.personRepository.findAll();
    }
    //Get person by id
    @GetMapping(path="/{id}")
    public Optional<Person> getPersonById(@PathVariable Integer id) {
        return this.personRepository.findById(id);
    }
    //Get todos for a person with id
    @GetMapping("/{id}/todos")
    public Iterable<Todo> getTodosForPerson(@PathVariable Integer id) {
        System.out.println("Getting todos for person with ID: " + id);
        Optional<Person> personOptional = this.personRepository.findById(id);
        if (personOptional.isEmpty()){
            System.out.println("Person with ID: " + id + " not found");
            return null;
        }else {
            Person personChoice = personOptional.get();
            return personChoice.getTodos();
        }
    }
    //Get todos for a person before a particular date
    @GetMapping("{id}/todos/")
    public Iterable<Todo> getTodosBeforeDate(@PathVariable Integer id, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date due_date) {
        System.out.println("Getting todos before date: " + due_date.toString() + "for person with ID: " + id);
        Optional<Person> personOptional = this.personRepository.findById(id);
        if (personOptional.isEmpty()){
            System.out.println("Person with ID: " + id + " not found");
            return null;
        } else {
            Person personChoice = personOptional.get();
            return personChoice.getTodosBeforeDate(due_date);
        }
    }

    //Post request URL api/person
    @PostMapping("/addPerson")
    public Person addPerson(@RequestBody Person person) {
        System.out.println("creating new person: " + person.toString());
        return this.personRepository.save(person);
    }
    //Updating a person
    @PutMapping("/{id}/updatePerson")
    public Person updatePerson(@RequestBody Person person, @PathVariable Integer id) {
        System.out.println("Updating person with ID: " + id);
        Optional<Person> personToUpdate = this.personRepository.findById(id);
        if (personToUpdate.isPresent()) {
            Person personUpdate = personToUpdate.get();
            if (person.getName() != null){
                personUpdate.setName(person.getName());
            }
            if (person.getEmail() != null) {
                personUpdate.setEmail(person.getEmail());
            }
            if (person.getPassword() != null) {
                personUpdate.setPassword(person.getPassword());
            }
            return this.personRepository.save(personUpdate);
        } else {
            System.out.println("Could not find person.");
            return null;
        }
    }

    @DeleteMapping("/deletePerson/{id}")
    public Person deletePerson(@PathVariable Integer id) {
        Optional<Person> personToDelete = this.personRepository.findById(id);
        if (personToDelete.isPresent()) {
            this.personRepository.delete(personToDelete.get());
            return personToDelete.get();
        } else {
            System.out.println("Person does not exist.");
            return null;
        }
    }
}
