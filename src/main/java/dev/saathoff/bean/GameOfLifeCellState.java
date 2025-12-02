package dev.saathoff.bean;

public enum GameOfLifeCellState {
    ALIVE("X"),
    DEAD("0");

    private final String displayValue;

    GameOfLifeCellState(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
