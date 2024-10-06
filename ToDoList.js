// src/components/ToDoList.js
import React from 'react';
import ToDoItem from './ToDoItem';

const ToDoList = ({ todos, deleteToDo }) => {
    console.log('Rendering ToDoList with todos:', todos);
    return (
        <ul>
            {todos.map((todo) => (
                <ToDoItem key={todo.id} todo={todo} deleteToDo={deleteToDo} />
            ))}
        </ul>
    );
};

export default ToDoList;
