package dev.saathoff.io.input.impl;

import dev.saathoff.io.input.AbstractInputService;
import dev.saathoff.io.input.InputSource;
import dev.saathoff.io.input.converter.impl.CancellableConverter;
import dev.saathoff.io.input.converter.impl.IntegerInputConverter;
import dev.saathoff.io.input.validator.InputValidator;
import dev.saathoff.io.input.validator.impl.CancellableValidator;
import dev.saathoff.io.output.OutputService;

import java.util.Collection;
import java.util.Optional;

public class CancellableIntegerSelectionInput extends AbstractInputService<Optional<Integer>, Collection<Integer>> {
    public CancellableIntegerSelectionInput(OutputService outputService, InputSource inputSource, IntegerInputConverter converter, InputValidator<Integer, Collection<Integer>> validator) {
        super(outputService, inputSource, new CancellableConverter<>(converter, "q"), new CancellableValidator<>(validator));
    }
}
