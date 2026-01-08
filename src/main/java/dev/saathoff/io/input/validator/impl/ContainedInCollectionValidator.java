package dev.saathoff.io.input.validator.impl;

import dev.saathoff.io.input.validator.InputValidator;
import dev.saathoff.io.input.validator.ValidationResult;

import java.util.Collection;

public class ContainedInCollectionValidator implements InputValidator<Integer, Collection<Integer>> {
    @Override
    public ValidationResult validate(Integer input, Collection<Integer> criteria) {
        if (criteria.contains(input)) {
            return ValidationResult.ok();
        }
        return ValidationResult.fail("Your input is not an option!");
    }
}
