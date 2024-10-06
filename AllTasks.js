import React from 'react';

const AllTasks = ({ todos }) => {
    return (
        <div>
            <h2>All Tasks</h2>
            <ul>
                {todos.map((todo) => (
                    <li key={todo.id}>{todo.task}</li>
                ))}
            </ul>
        </div>
    );
};

export default AllTasks;
