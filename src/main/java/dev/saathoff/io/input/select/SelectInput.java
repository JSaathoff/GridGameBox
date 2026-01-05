package dev.saathoff.io.input.select;

import java.util.Map;

public interface SelectInput {
    <T extends Selectable> T select(String label, Map<Integer, T> options);
    <T extends Enum<T> & Selectable> T selectFromEnum(String label, Class<T> enumClass);
}
