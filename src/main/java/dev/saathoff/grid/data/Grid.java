package dev.saathoff.grid.data;

import dev.saathoff.game.data.Cell;

public interface Grid<T extends Cell> {
    void setCell(int row, int column, T cell);

    T getCell(int row, int column);

    T getCell(Coordinate cord);

    int getRowCount();

    int getColumnCount();

}
