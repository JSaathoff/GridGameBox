package dev.saathoff.service;

import dev.saathoff.bean.Cell;
import dev.saathoff.bean.Grid;

public class CellStateCalculationService {

    private int countAliveNeighbors(Grid grid, int row, int column) {

        int aliveNeighbors = 0;

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
                    if(neighborCell.isAlive()){
                        aliveNeighbors++;
                    }
                }
            }
        }

        return aliveNeighbors;
    }
    public Cell calculateCellState(Grid grid, int row, int column) {
        Cell cell = grid.getGrid().get(row).get(column);
        Cell nextGenCell = this.copyCell(cell);
        int aliveNeighbors = this.countAliveNeighbors(grid, row, column);
        if(cell.isAlive() && (aliveNeighbors < 2 || aliveNeighbors > 3)){
                nextGenCell.setAlive(false);
        }
        if(!cell.isAlive() && aliveNeighbors == 3){
            nextGenCell.setAlive(true);
        }
        return nextGenCell;
    }

    private Cell copyCell(Cell cell) {
        Cell nextGenCell = new Cell();
        nextGenCell.setAlive(cell.isAlive());
        return nextGenCell;
    }
}
