package dev.saathoff.io.input.validator.impl;

import dev.saathoff.io.input.validator.InputValidator;
import dev.saathoff.io.input.validator.ValidationResult;

import java.util.Optional;

public class CancellableValidator<T, V> implements InputValidator<Optional<T>, V> {
    private final InputValidator<T, V> baseValidator;

    public CancellableValidator(InputValidator<T, V> baseValidator) {
        this.baseValidator = baseValidator;
    }

    @Override
    public ValidationResult validate(Optional<T> input, V criteria) {
        if (input.isEmpty()) {
            return ValidationResult.ok();
        }

        return baseValidator.validate(input.get(), criteria);
    }
}