package dev.saathoff.io.input;

public interface Input<I, V> {

    I getInput(String label, V criteria);
}
