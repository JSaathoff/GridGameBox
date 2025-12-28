package dev.saathoff.bean;

import dev.saathoff.service.grid.dto.Coordinates;

public interface Grid<T> {
    void setCell(int row, int column, T cell);

    T getCell(int row, int column);

    T getCell(Coordinates cord);

    int getRowCount();

    int getColumnCount();

}
