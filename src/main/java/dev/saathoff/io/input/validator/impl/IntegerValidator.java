package dev.saathoff.io.input.validator.impl;

import dev.saathoff.io.input.validator.InputValidator;
import dev.saathoff.io.input.validator.ValidationResult;
import dev.saathoff.io.input.validator.criteria.RangeValidationCriteria;

public class IntegerValidator implements InputValidator<Integer, RangeValidationCriteria> {
    @Override
    public ValidationResult validate(Integer input, RangeValidationCriteria criteria) {
        if (input < criteria.min()) {
            return ValidationResult.fail("Number has to be bigger than " + criteria.min());
        }
        if (input > criteria.max()) {
            return ValidationResult.fail("Number has to be lower than " + criteria.max());
        }
        return ValidationResult.ok();
    }
}
