package com.example.taskscheduler.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "todo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Todo {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;
    @Setter
    @Column
    private Date due_date;
    @Getter
    @Column
    private Date date_added;
    @Column
    private Integer person_id;

    public Date getDue_Date() {
        return due_date;
    }

    public String toString() {
        return "Task: " + name + "Due: " + due_date.toString() + "Added on: " + date_added.toString();
    }
}
