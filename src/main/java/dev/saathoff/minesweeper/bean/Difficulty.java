package dev.saathoff.minesweeper.bean;

import dev.saathoff.io.input.select.Selectable;

public enum Difficulty implements Selectable {
    EASY(10, 8, 8),
    MEDIUM(40, 16, 16),
    HARD(99, 16, 30);

    private final int mineCount;
    private final int rowCount;
    private final int columnCount;

    Difficulty(int mineCount, int rowCount, int columnCount) {
        this.mineCount = mineCount;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    public int getMineCount() { return mineCount; }
    public int getRowCount() { return rowCount; }
    public int getColumnCount() { return columnCount; }

    @Override
    public String getLabelForSelection() {
        return String.format("%s (%dx%d, %d mines)",
                name(), rowCount, columnCount, mineCount);
    }
}
