package dev.saathoff.bean;

import java.util.List;

public class Cell {


    private MinesweeperCellState cellState;

    private String displayValue;

    private Cell[][] neighbors;

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public Cell[][] getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Cell[][] neighbors) {
        this.neighbors = neighbors;
    }

    public MinesweeperCellState getCellState() {
        return cellState;
    }

    public void setCellState(MinesweeperCellState cellState) {
        this.cellState = cellState;
    }
}
