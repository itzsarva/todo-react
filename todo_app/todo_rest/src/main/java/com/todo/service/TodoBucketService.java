package com.todo.service;

import com.todo.domain.TodoBucket;
import com.todo.modal.TodoBucketRequest;

import java.util.List;

public interface TodoBucketService {

    List<TodoBucket> getAllTodoBuckets(String username);

    Boolean addTodoBucket(TodoBucketRequest todoBucketRequest);

    Boolean removeTodoBucket(TodoBucketRequest todoBucketRequest);
}
