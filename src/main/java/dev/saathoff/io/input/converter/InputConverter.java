package dev.saathoff.io.input.converter;

public interface InputConverter <T> {
    T convert(String rawInput) throws IllegalArgumentException;
}
