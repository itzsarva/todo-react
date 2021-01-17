package com.todo.serviceImpl;

import com.todo.domain.Todo;
import com.todo.domain.TodoBucket;
import com.todo.modal.TodoRequest;
import com.todo.repository.TodoBucketRepository;
import com.todo.repository.TodoRepository;
import com.todo.security.services.UserDetailsImpl;
import com.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TodoBucketRepository todoBucketRepository;

    private Authentication getUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public Boolean addTodo(TodoRequest todoRequest) {
        Authentication authentication = getUser();
        if (authentication.isAuthenticated()) {
            TodoBucket todoBucket = todoBucketRepository.findById(todoRequest.getParentId()).get();
            Todo todo = Todo.builder()
                    .name(todoRequest.getName())
                    .completed(Boolean.FALSE)
                    .todoBucket(todoBucket)
                    .build();
            todoRepository.save(todo);
            return Boolean.TRUE;
        } else {
            throw new AuthenticationServiceException("Invalid Access");
        }
    }

    @Override
    public Boolean removeTodo(TodoRequest todoRequest) {
        Authentication authentication = getUser();
        if (authentication.isAuthenticated()) {
            TodoBucket todoBucket = todoBucketRepository.findById(todoRequest.getParentId()).get();
            todoBucket.getTodoSet().removeIf(todos -> todos.getId().equals(todoRequest.getChildId()));
            todoBucketRepository.save(todoBucket);
            return Boolean.TRUE;
        } else {
            throw new AuthenticationServiceException("Invalid Access");
        }
    }

    @Override
    public Boolean updateTodo(TodoRequest todoRequest) {
        Authentication authentication = getUser();
        if (authentication.isAuthenticated()) {
            TodoBucket todoBucket = todoBucketRepository.findById(todoRequest.getParentId()).get();
            todoBucket.getTodoSet().stream().filter(todos -> todos.getId().equals(todoRequest.getChildId()))
                    .forEach(todo -> todo.setCompleted(!todo.getCompleted()));
            todoBucketRepository.save(todoBucket);
            return Boolean.TRUE;
        } else {
            throw new AuthenticationServiceException("Invalid Access");
        }
    }
}
