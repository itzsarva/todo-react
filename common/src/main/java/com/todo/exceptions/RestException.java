package com.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestException {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TodoConstraintsException.class)
    public final ResponseEntity<Object> handleValidationExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        System.err.println(ex.getLocalizedMessage());
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Un processable entity", details);
        return new ResponseEntity(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
