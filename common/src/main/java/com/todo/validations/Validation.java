package com.todo.validations;

@FunctionalInterface
public interface Validation<T> {

    ValidationResult validate(T value);

}
