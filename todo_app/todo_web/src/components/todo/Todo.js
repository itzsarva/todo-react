import React from "react";
import '../../app.css';

const Todo = ({todo,completeTodo, removeTodo}) => {
    return (
        <div
            className="todo"
            style={{textDecoration: todo.completed ? "line-through" : ""}}
        >
            {todo.name}
            <div>
                <button onClick={() => completeTodo(todo.id)}>Complete</button>
                <button onClick={() => removeTodo(todo.id)}>x</button>
            </div>
        </div>
    );
};

export default Todo;