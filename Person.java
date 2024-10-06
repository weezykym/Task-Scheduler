package com.example.taskscheduler.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column
    private String name;

    @Column
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private List<Todo> todos;

    @Column
    private String Password;

    //Custom method to get tasks before due date
    public Iterable<Todo> getTodosBeforeDate(Date due) {
        ArrayList<Todo> todosBeforeDate = new ArrayList<>();
        for (Todo todo : this.todos) {
            if (todo.getDue_Date().before(due)) {
                todosBeforeDate.add(todo);
            }
        }
        return todosBeforeDate;
    }
    @Override
    public String toString() {
        return "Person ID: " + id +
                " Name: " + name;
    }
}
