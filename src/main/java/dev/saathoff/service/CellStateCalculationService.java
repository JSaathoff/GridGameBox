package dev.saathoff.service;

import dev.saathoff.bean.Cell;
import dev.saathoff.bean.MinesweeperCellState;

public class CellStateCalculationService {

    public Cell calculateCellState(Cell cell) {
        Cell nextGenCell = this.copyCell(cell);
        int aliveNeighbors = this.countAliveNeighbors(cell.getNeighbors());
        if(cell.getCellState() == MinesweeperCellState.ALIVE && (aliveNeighbors < 2 || aliveNeighbors > 3)){
                nextGenCell.setCellState(MinesweeperCellState.DEAD);
        }
        if(cell.getCellState() == MinesweeperCellState.DEAD && aliveNeighbors == 3){
            nextGenCell.setCellState(MinesweeperCellState.ALIVE);
        }
        return nextGenCell;
    }

    private Cell copyCell(Cell cell) {
        Cell nextGenCell = new Cell();
        nextGenCell.setCellState(cell.getCellState());
        nextGenCell.setNeighbors(cell.getNeighbors());
        return nextGenCell;
    }

    private int countAliveNeighbors(Cell[][] neighbors) {
        int aliveNeighbors = 0;
        for (Cell[] row : neighbors){
            for (Cell cell : row){
                if(cell != null && cell.getCellState() == MinesweeperCellState.ALIVE){
                    aliveNeighbors ++;
                }
            }
        }
        return aliveNeighbors;
    }
}
