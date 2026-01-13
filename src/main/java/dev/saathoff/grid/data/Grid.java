package dev.saathoff.grid.data;

import java.util.stream.Stream;

public interface Grid<T extends Cell> {
    void setCell(int row, int column, T cell);

    T getCell(int row, int column);

    T getCell(Coordinate cord);

    Stream<T> asStream();

    int getRowCount();

    int getColumnCount();

}
