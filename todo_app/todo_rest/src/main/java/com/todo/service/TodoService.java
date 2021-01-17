package com.todo.service;

import com.todo.modal.TodoRequest;

public interface TodoService {

    Boolean addTodo(TodoRequest todoRequest);

    Boolean removeTodo(TodoRequest todoRequest);

    Boolean updateTodo(TodoRequest todoRequest);

}
