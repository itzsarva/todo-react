package com.todo.controllers;


import com.todo.domain.TodoBucket;
import com.todo.modal.TodoBucketRequest;
import com.todo.modal.TodoRequest;
import com.todo.service.TodoBucketService;
import com.todo.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "register", tags = {"Todo Resource"})
@SwaggerDefinition(tags = {
        @Tag(name = "Todo Resource", description = "Operations pertaining to todo")
})
@Slf4j
public class TodoController {

    @Autowired
    TodoBucketService todoBucketService;

    @Autowired
    TodoService todoService;

    @GetMapping("/todo/get/todos/{username}")
    public ResponseEntity<List<TodoBucket>> getAllTodos(@PathVariable String username) {
        log.info("loading all todos for user " + username);
        List<TodoBucket> todoBucketList = todoBucketService.getAllTodoBuckets(username);
        log.info("loaded all todos for " + username);
        return ResponseEntity.ok(todoBucketList);
    }


    @PostMapping("/todo/add/bucket")
    public ResponseEntity<?> addTodoBucket(@RequestBody TodoBucketRequest todoBucketRequest) {
        log.info("addTodoBucket called");
        todoBucketService.addTodoBucket(todoBucketRequest);
        log.info("todoBucketService.addTodoBucket successfully returned");
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/todo/delete/bucket")
    public ResponseEntity<?> deleteTodoBucket(@RequestBody TodoBucketRequest todoBucketRequest) {
        log.info("deleteTodoBucket called");
        todoBucketService.removeTodoBucket(todoBucketRequest);
        log.info("todoBucketService.removeTodoBucket successfully returned");
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/todo/add/todo")
    public ResponseEntity<?> addTodo(@RequestBody TodoRequest todoRequest) {
        log.info("addTodo called");
        todoService.addTodo(todoRequest);
        log.info("todoService.addTodo successfully returned");
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/todo/delete/todo")
    public ResponseEntity<?> deleteTodo(@RequestBody TodoRequest todoRequest) {
        log.info("deleteTodo called");
        todoService.removeTodo(todoRequest);
        log.info("todoService.removeTodo successfully returned");
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/todo/update/todo")
    public ResponseEntity<?> updateTodo(@RequestBody TodoRequest todoRequest) {
        log.info("updateTodo called");
        todoService.updateTodo(todoRequest);
        log.info("todoService.updateTodo successfully returned");
        return ResponseEntity.ok("Success");
    }

}
