package com.example.taskscheduler.controller;

import com.example.taskscheduler.entity.Todo;
import com.example.taskscheduler.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api")
@RestController

public class TodoController {

    @Autowired
    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    @GetMapping
    public Iterable<Todo> getAllTodos(){
        return this.todoRepository.findAll();
    }
    @GetMapping("/todos/{id}")
    public Optional<Todo> getTodosById(@PathVariable Integer id) {
        System.out.println("Getting todo by id: " + id);
        return this.todoRepository.findById(id);
    }

    @PostMapping("/addTodo")
    public Todo addTodo(@RequestBody Todo todo) {
        return this.todoRepository.save(todo);
    }

    @PutMapping("/updateTodo")
    public Todo updateTodo(@RequestBody Todo todo, @PathVariable Integer id) {
        System.out.println("Updating todo: " + id);
        Optional<Todo> todoToUpdate = this.todoRepository.findById(id);
        if (todoToUpdate.isPresent()) {
            Todo todoUpdate = todoToUpdate.get();
            if (todo.getName() != null) {
                todoUpdate.setName(todo.getName());
            }
            if (todo.getDue_Date() != null) {
                todoUpdate.setDue_date(todo.getDue_Date());
            }
            if (todo.getDate_added() != null) {
                todoUpdate.setDate_added(todo.getDate_added());
            }
            if (todo.getPerson_id() != null) {
                todoUpdate.setPerson_id(todo.getPerson_id());
            }
            return this.todoRepository.save(todoUpdate);
        } else {
            System.out.println("Todo not found");
            return null;
        }
    }

    //Delete task by id
    @DeleteMapping("/deleteTodo")
    public Todo deleteTodo(@PathVariable Integer id) {
        Optional<Todo> todoToDelete = this.todoRepository.findById(id);
        if (todoToDelete.isPresent()) {
            this.todoRepository.delete(todoToDelete.get());
            System.out.println("The task has been deleted");
            return todoToDelete.get();
        } else {
            System.out.println("The task cannot be found.");
            return null;
        }
    }
}
