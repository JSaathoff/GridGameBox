package dev.saathoff.service;

import dev.saathoff.bean.Cell;
import dev.saathoff.bean.Grid;

import java.util.ArrayList;
import java.util.List;

public class GridService {

    public Grid generateNewGrid(int rows, int columns) {
        Grid grid = new Grid();
        grid.setRowCount(rows);
        grid.setColumnCount(columns);
        grid.setGrid(new ArrayList<>());
        for (int i = 0; i < rows; i++){
            grid.getGrid().add(new ArrayList<>());
            for(int j = 0; j < columns; j++){
                Cell cell = new Cell();
                cell.setAlive(false);
                grid.getGrid().get(i).add(cell);
            }
        }

        return grid;
    }

    public Grid calculateNextGridState(Grid grid, CellStateCalculationService cellStateCalculationService) {
        Grid nextGenGrid = this.generateNewGrid(grid.getRowCount(), grid.getColumnCount());

        for(int i = 0; i < grid.getRowCount(); i++){
            for (int j = 0; j <grid.getColumnCount(); j++){
                Cell nextGenCell = cellStateCalculationService.calculateCellState(grid, i, j);
                nextGenGrid.getGrid().get(i).set(j, nextGenCell);
            }
        }
        return nextGenGrid;
    }
}
