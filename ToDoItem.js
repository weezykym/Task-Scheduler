// src/components/ToDoItem.js
import React from 'react';

const ToDoItem = ({ todo, deleteToDo }) => {
    console.log('Rendering ToDoItem:', todo);
    return (
        <li>
            {todo.task}
            <button onClick={() => deleteToDo(todo.id)}>Delete</button>
        </li>
    );
};

export default ToDoItem;
