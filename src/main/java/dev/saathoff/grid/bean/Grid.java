package dev.saathoff.grid.bean;

public interface Grid<T> {
    void setCell(int row, int column, T cell);

    T getCell(int row, int column);

    T getCell(Coordinates cord);

    int getRowCount();

    int getColumnCount();

}
