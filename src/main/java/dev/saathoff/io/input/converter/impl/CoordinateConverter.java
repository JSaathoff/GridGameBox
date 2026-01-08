package dev.saathoff.io.input.converter.impl;

import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.io.input.converter.InputConverter;

public class CoordinateConverter implements InputConverter<Coordinate> {
    @Override
    public Coordinate convert(String rawInput) throws IllegalArgumentException {
        String[] parts = rawInput.trim().split("[,\\s]+");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Please enter two numbers separated by a comma or space (e.g.'3,4' '3 4')");
        }

        try {
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            return new Coordinate(row, col);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Inputs must be valid integers.");
        }
    }
}
