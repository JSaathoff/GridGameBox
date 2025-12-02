package dev.saathoff.service;

import dev.saathoff.bean.Cell;
import dev.saathoff.bean.Grid;
import dev.saathoff.bean.GameOfLifeCellState;

import java.util.ArrayList;
import java.util.List;

public class GridService {

    public String displayGridState(Grid grid){
        StringBuilder builder = new StringBuilder();
        List<List<Cell>> gridList = grid.getGrid();
        for (List<Cell> row : gridList){
            for(Cell cell: row){
                builder.append(cell.getCellState().getDisplayValue());
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public Grid generateNewGrid(int rows, int columns) {
        Grid grid = new Grid();
        grid.setRowCount(rows);
        grid.setColumnCount(columns);
        grid.setGrid(new ArrayList<>());
        for (int i = 0; i < rows; i++){
            grid.getGrid().add(new ArrayList<>());
            for(int j = 0; j < columns; j++){
                Cell cell = new Cell();
                cell.setCellState(GameOfLifeCellState.DEAD);
                grid.getGrid().get(i).add(cell);
            }
        }
        for (int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                Cell[][] neighbors = this.determineNeighbors(grid, i, j);
                grid.getGrid().get(i).get(j).setNeighbors(neighbors);
            }
        }

        return grid;
    }

    private Cell[][] determineNeighbors(Grid grid, int row, int column) {
        Cell[][] neighbors = new Cell[3][3];


        for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
            for (int deltaCol = -1; deltaCol <= 1; deltaCol++) {

                if (deltaRow == 0 && deltaCol == 0) {
                    continue;
                }

                int neighborRow = row + deltaRow;
                int neighborCol = column + deltaCol;

                boolean isWithinBounds =
                        neighborRow >= 0 && neighborRow < grid.getRowCount() &&
                                neighborCol >= 0 && neighborCol < grid.getColumnCount();

                if (isWithinBounds) {
                    Cell neighborCell = grid.getGrid().get(neighborRow).get(neighborCol);
                    neighbors[deltaRow + 1][deltaCol + 1] = neighborCell;
                }
            }
        }

        return neighbors;
    }

    public Grid calculateNextGridState(Grid grid, CellStateCalculationService cellStateCalculationService) {
        List<List<Cell>> gridList = grid.getGrid();

        Grid nextGenGrid = this.generateNewGrid(grid.getRowCount(), grid.getColumnCount());

        for(int i = 0; i < grid.getRowCount(); i++){
            for (int j = 0; j <grid.getColumnCount(); j++){
                Cell cell = gridList.get(i).get(j);
                Cell nextGenCell = cellStateCalculationService.calculateCellState(cell);
                nextGenGrid.getGrid().get(i).set(j, nextGenCell);
            }
        }
        return nextGenGrid;
    }
}
