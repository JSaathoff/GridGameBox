package dev.saathoff.io.input.converter.impl;

import dev.saathoff.io.input.converter.InputConverter;

public class IntegerInputConverter implements InputConverter<Integer> {
    @Override
    public Integer convert(String rawInput) throws IllegalArgumentException {
        int convertedValue;
        try {
            convertedValue = Integer.parseInt(rawInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Enter a valid integer number!");
        }
        return convertedValue;
    }
}
