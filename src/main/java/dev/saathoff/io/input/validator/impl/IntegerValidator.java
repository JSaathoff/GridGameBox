package dev.saathoff.io.input.validator.impl;

import dev.saathoff.io.input.validator.InputValidator;
import dev.saathoff.io.input.validator.criteria.IntegerValidationCriteria;
import dev.saathoff.io.input.validator.ValidationResult;

public class IntegerValidator implements InputValidator<Integer, IntegerValidationCriteria> {
    @Override
    public ValidationResult validate(Integer input, IntegerValidationCriteria criteria) {
        if(input < criteria.min()){
            return ValidationResult.fail("Number has to be bigger than " + criteria.min());
        }
        if( input > criteria.max()){
            return ValidationResult.fail("Number has to be lower than " + criteria.max());
        }
        return ValidationResult.ok();
    }
}
