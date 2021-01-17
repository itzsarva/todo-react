package com.todo.bootstrap;

import com.todo.domain.Todo;
import com.todo.domain.TodoBucket;
import com.todo.domain.UserDetails;
import com.todo.security.utils.ROLES;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Component
@Slf4j
@Transactional
public class TodoDataLoad implements CommandLineRunner {

    private final SessionFactory sessionFactory;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public TodoDataLoad(SessionFactory sessionFactory, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.sessionFactory = sessionFactory;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private void createUsers() {
        UserDetails userDetails = UserDetails.builder()
                .username("test")
                .password(bCryptPasswordEncoder.encode("test"))
                .role(ROLES.ADMIN.getRole())
                .build();
        UserDetails userDetailsTest = UserDetails.builder()
                .username("test1")
                .password(bCryptPasswordEncoder.encode("test1"))
                .role(ROLES.ADMIN.getRole())
                .build();
        List<UserDetails> userDetailList = new ArrayList() {{
            add(userDetails);
            add(userDetailsTest);
        }};
        userDetailList.forEach(val -> getSession().saveOrUpdate(val));
    }

    private void createTodoBuckets() {
        Arrays.asList(getBucket("Football", "Score an overhead goal?", "play without breaking ankle?"
                , "three top bins"),
                getBucket("Trips", "London", "Alaska", "azkaban if possible?"),
                getBucket("All time", "sleep sound", "sleep well", "try to sleep"))
                .forEach(val -> getSession().saveOrUpdate(val));
    }

    private TodoBucket getBucket(String bucketName, String... todos) {
        TodoBucket bucket = TodoBucket.builder()
                .name(bucketName)
                .user("test")
                .build();

        Set<Todo> todoSet = new HashSet<>();

        Arrays.stream(todos).forEach(todoText -> {
            Todo todo = Todo.builder()
                    .completed(false)
                    .todoBucket(bucket)
                    .name(todoText)
                    .build();
            todoSet.add(todo);
        });
        bucket.setTodoSet(todoSet);
        return bucket;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Started Data loading on start up....");
        createUsers();
        log.info("Loading users completed....");
        createTodoBuckets();
        log.info("All Data load Completed....");
    }
}
