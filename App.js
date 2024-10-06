// src/App.js
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import AddToDo from './components/AddToDo';
import ToDoList from './components/ToDoList';

const App = () => {
    const [todos, setTodos] = useState([]);

    useEffect(() => {
        console.log('Fetching todos');
        fetchTodos();
    }, []);

    const fetchTodos = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/todos');
            console.log('Fetched todos:', response.data);
            setTodos(response.data);
        } catch (error) {
            console.error('Error fetching todos:', error);
        }
    };

    const addToDo = async (task) => {
        try {
            const response = await axios.post('http://localhost:8080/api/todos?toDo='+task);
            console.log('Added todo:', response.data);
            setTodos([...todos, response.data]);
        } catch (error) {
            console.error('Error adding todo:', error);
        }
    };

    const deleteToDo = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/api/todos/${id}`);
            console.log('Deleted todo with id:', id);
            setTodos(todos.filter(todo => todo.id !== id));
        } catch (error) {
            console.error('Error deleting todo:', error);
        }
    };

    return (
        <div className="App">
            <h1>Todo List</h1>
            <AddToDo addToDo={addToDo} />
            <ToDoList todos={todos} deleteToDo={deleteToDo} />
        </div>
    );
};

export default App;
