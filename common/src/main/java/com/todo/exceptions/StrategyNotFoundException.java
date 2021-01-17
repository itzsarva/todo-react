package com.todo.exceptions;

public class StrategyNotFoundException extends RuntimeException {

    public static final long serialVersionUID = 1l;

    public StrategyNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
