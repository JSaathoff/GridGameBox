package dev.saathoff.io.input.validator;

@FunctionalInterface
public interface InputValidator<I, V> {
    ValidationResult validate(I input, V criteria);
}