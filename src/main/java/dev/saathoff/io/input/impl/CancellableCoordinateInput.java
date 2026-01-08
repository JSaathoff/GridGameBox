package dev.saathoff.io.input.impl;

import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.io.input.AbstractInputService;
import dev.saathoff.io.input.InputSource;
import dev.saathoff.io.input.converter.impl.CancellableConverter;
import dev.saathoff.io.input.converter.impl.CoordinateConverter;
import dev.saathoff.io.input.validator.criteria.CoordinateValidationCriteria;
import dev.saathoff.io.input.validator.impl.CancellableValidator;
import dev.saathoff.io.input.validator.impl.CoordinateValidator;
import dev.saathoff.io.output.OutputService;

import java.util.Optional;

public class CancellableCoordinateInput extends AbstractInputService<Optional<Coordinate>, CoordinateValidationCriteria> {
    public CancellableCoordinateInput(
            OutputService output,
            InputSource source,
            CoordinateConverter baseConverter,
            CoordinateValidator validator) {

        super(output, source, new CancellableConverter<>(baseConverter, "q"), new CancellableValidator<>(validator));
    }
}
