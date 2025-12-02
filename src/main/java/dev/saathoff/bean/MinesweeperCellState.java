package dev.saathoff.bean;

public enum MinesweeperCellState {
    ALIVE("X"),
    DEAD("0");

    private final String displayValue;

    MinesweeperCellState(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
