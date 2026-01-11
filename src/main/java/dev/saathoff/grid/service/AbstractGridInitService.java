package dev.saathoff.grid.service;

import dev.saathoff.game.data.Cell;
import dev.saathoff.grid.data.ArrayGrid;
import dev.saathoff.grid.data.Grid;

public abstract class AbstractGridInitService<T extends Cell> {

    public Grid<T> generateNewGrid(int rows, int columns) {
        Grid<T> grid = new ArrayGrid<>(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                T cell = this.initializeCell();

                grid.setCell(i, j, cell);
            }
        }
        return grid;
    }

    protected abstract T initializeCell();

}
