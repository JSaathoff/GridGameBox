package dev.saathoff.grid.service;

import dev.saathoff.grid.bean.ArrayGrid;
import dev.saathoff.grid.bean.Grid;
import dev.saathoff.gameoflife.service.CellStateCalculationService;

public abstract class AbstractGridService<T> {

    public Grid<T> generateNewGrid(int rows, int columns) {
        Grid<T> grid = new ArrayGrid<>(rows, columns);
        for (int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                T cell = this.initializeCell();
                grid.setCell(i,j, cell);
            }
        }
        return grid;
    }

    public abstract T initializeCell();


    public Grid<T> calculateNextGridState(Grid<T> grid, CellStateCalculationService cellStateCalculationService) {
        Grid<T> nextGenGrid = this.generateNewGrid(grid.getRowCount(), grid.getColumnCount());

        for(int i = 0; i < grid.getRowCount(); i++){
            for (int j = 0; j <grid.getColumnCount(); j++){
                T nextGenCell = this.nextGenerationState(grid, i, j);
                nextGenGrid.setCell(i, j, nextGenCell);
            }
        }
        return nextGenGrid;
    }

    public abstract T nextGenerationState(Grid<T> grid, int row, int column);


}
