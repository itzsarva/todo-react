package com.todo.repository;

import com.todo.domain.TodoBucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoBucketRepository extends JpaRepository<TodoBucket, Long> {

    List<TodoBucket> getAllTodoBucketByUser(String user);
}
