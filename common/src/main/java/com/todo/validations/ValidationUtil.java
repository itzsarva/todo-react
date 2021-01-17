package com.todo.validations;

import com.todo.exceptions.TodoConstraintsException;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class ValidationUtil {

    private ValidationUtil() {
        throw new IllegalArgumentException("Cannot be initialized");
    }

    public static <T> List<ValidationResult> validateAll(Collection<Validation> validations, T value) throws TodoConstraintsException {
        List<ValidationResult> validationResults = new ArrayList<>();
        for (Validation validation : validations) {
            validationResults.add(validation.validate(value));
        }

        List<ValidationResult> validationErrors = validationResults.stream().filter(val -> !val.isValid()).collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(validationErrors)) {
            throw new TodoConstraintsException(validationErrors);
        }
        return validationResults;
    }
}
