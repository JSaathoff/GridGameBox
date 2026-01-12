package dev.saathoff.io.input.impl;

import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.io.input.AbstractInputService;
import dev.saathoff.io.input.CancellableInput;
import dev.saathoff.io.input.InputSource;
import dev.saathoff.io.input.converter.impl.CancellableConverter;
import dev.saathoff.io.input.converter.impl.CoordinateConverter;
import dev.saathoff.io.input.validator.criteria.CoordinateValidationCriteria;
import dev.saathoff.io.input.validator.impl.CancellableValidator;
import dev.saathoff.io.input.validator.impl.CoordinateValidator;
import dev.saathoff.io.output.OutputService;

import java.util.Optional;

public class CancellableCoordinateInput extends AbstractInputService<Optional<Coordinate>, CoordinateValidationCriteria> implements CancellableInput<Optional<Coordinate>, CoordinateValidationCriteria> {

    public static final String CANCEL_COMMAND = "q";

    public CancellableCoordinateInput(
            OutputService output,
            InputSource source,
            CoordinateConverter baseConverter,
            CoordinateValidator validator) {

        super(output, source, new CancellableConverter<>(baseConverter, CANCEL_COMMAND), new CancellableValidator<>(validator));
    }

    @Override
    public String getCancelCommand() {
        return CANCEL_COMMAND;
    }
}
