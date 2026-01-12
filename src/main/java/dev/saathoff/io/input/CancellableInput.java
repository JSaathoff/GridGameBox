package dev.saathoff.io.input;

public interface CancellableInput<I, V> extends Input<I, V> {
    String getCancelCommand();
}
