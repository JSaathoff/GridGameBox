package dev.saathoff.io.input.validator;

public record ValidationResult(boolean isValid, String errorMessage) {
    public static ValidationResult ok() {
        return new ValidationResult(true, null);
    }
    public static ValidationResult fail(String message) {
        return new ValidationResult(false, message);
    }
}