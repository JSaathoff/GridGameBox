package dev.saathoff.io.input.select;

import java.util.Map;
import java.util.Optional;

public interface CancellableSelectInput {
    <T extends Selectable> Optional<T> select(String label, Map<Integer, T> options);

    <T extends Enum<T> & Selectable> Optional<T> selectFromEnum(String label, Class<T> enumClass);

    String getCancelCommand();
}
