package dev.saathoff.io.input.converter.impl;

import dev.saathoff.io.input.converter.InputConverter;

import java.util.Optional;

public class CancellableConverter<T> implements InputConverter<Optional<T>> {

    private final InputConverter<T> baseConverter;

    private final String exitCommand;

    public CancellableConverter(InputConverter<T> baseConverter, String exitCommand) {
        this.baseConverter = baseConverter;
        this.exitCommand = exitCommand;
    }

    @Override
    public Optional<T> convert(String rawInput) throws IllegalArgumentException {
        if (rawInput.trim().equalsIgnoreCase(exitCommand)) {
            return Optional.empty();
        }

        try {
            T result = baseConverter.convert(rawInput);
            return Optional.of(result);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage() + " (or type '" + exitCommand + "' to cancel)");
        }
    }
}
