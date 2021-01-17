import React, {useEffect} from "react";
import Todo from "./Todo";
import '../../app.css';
import {post} from "../util/RestClient";
import {TODO_URL_API} from "../util/TodoConstants";

function TodoForm({addTodo}) {
    const [value, setValue] = React.useState("");

    const handleSubmit = e => {
        e.preventDefault();
        if (!value) return;
        addTodo(value);
        setValue("");
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="name"
                className="input"
                value={value}
                onChange={e => setValue(e.target.value)}
            />
            <button>Add</button>
        </form>
    );
}

const TodoList = ({parentId, parentName , todoList, setPageRefresh, refresh}) => {

    const [todos, setTodos] = React.useState(todoList);

    useEffect(() => {
        setTodos([...todoList]);
    }, [todoList]);

    const completeTodo = id => {
        post(TODO_URL_API + 'update/todo', {childId: id, parentId: parentId})
            .then(res => {
                setPageRefresh(!refresh);
            });
    };

    const createTodo = (parentId, name) => {
        return {parentId: parentId, name: name}
    };

    const addTodo = (name) => {
        post(TODO_URL_API + 'add/todo', createTodo(parentId, name))
            .then(res => {
                setPageRefresh(!refresh);
            });
    };

    const removeTodo = id => {
        post(TODO_URL_API + 'delete/todo', {childId: id, parentId: parentId})
            .then(res => {
                setPageRefresh(!refresh);
            });
    };

    return (
        <div className="app">
            <h3 style={{color: "black"}}>To do list for : {parentName}</h3>
            <br/>
            <div className="todo-list">
                {todos.map((todo) => (
                    <Todo
                        key={todo.id}
                        todo={todo}
                        completeTodo={completeTodo}
                        removeTodo={removeTodo}
                    />
                ))}
                <TodoForm addTodo={addTodo}/>
            </div>
        </div>
    );
}

export default TodoList;