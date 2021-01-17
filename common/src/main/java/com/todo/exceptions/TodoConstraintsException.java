package com.todo.exceptions;

import com.todo.validations.ValidationResult;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TodoConstraintsException extends RuntimeException {

    public static final long serialVersionUID = 1l;

    private final transient List<ValidationResult> validationResults;

    public TodoConstraintsException(final List<ValidationResult> validationResults) {
        super(validationResults
                .stream().map(ValidationResult::getValidationMessage)
                .reduce((msg1, msg2) -> new StringBuilder().append(msg1).append(",").append(msg2).toString())
                .orElse(""));
        this.validationResults = new ArrayList<>(validationResults);
    }
}
