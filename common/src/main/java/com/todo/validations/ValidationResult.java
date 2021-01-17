package com.todo.validations;

import lombok.Data;

@Data
public class ValidationResult {

    private final Boolean valid;

    private String validationMessage;

    public ValidationResult(Boolean valid, String validationMessage) {
        this.valid = valid;
        this.validationMessage = validationMessage;
    }

    public ValidationResult(Boolean valid) {
        this.valid = valid;
    }

    public static ValidationResult ok() {
        return new ValidationResult(Boolean.TRUE);
    }

    public static ValidationResult fail() {
        return new ValidationResult(Boolean.FALSE);
    }

    public Boolean isValid() {
        return this.valid;
    }

    public String getValidationMessage() {
        return this.validationMessage;
    }

}
