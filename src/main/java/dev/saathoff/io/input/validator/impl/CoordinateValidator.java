package dev.saathoff.io.input.validator.impl;

import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.io.input.validator.InputValidator;
import dev.saathoff.io.input.validator.ValidationResult;
import dev.saathoff.io.input.validator.criteria.CoordinateValidationCriteria;

import java.util.ArrayList;
import java.util.List;

public class CoordinateValidator implements InputValidator<Coordinate, CoordinateValidationCriteria> {
    @Override
    public ValidationResult validate(Coordinate input, CoordinateValidationCriteria criteria) {
        List<String> errors = new ArrayList<>();

        if (input.row() < criteria.minRow()) errors.add("Row must be >= " + criteria.minRow());
        if (input.row() > criteria.maxRow()) errors.add("Row must be <= " + criteria.maxRow());
        if (input.column() < criteria.minCol()) errors.add("Column must be >= " + criteria.minCol());
        if (input.column() > criteria.maxCol()) errors.add("Column must be <= " + criteria.maxCol());

        return errors.isEmpty()
                ? ValidationResult.ok()
                : ValidationResult.fail(String.join("\n", errors));
    }
}
