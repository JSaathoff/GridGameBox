package dev.saathoff.bean;

public class Cell {


    private GameOfLifeCellState cellState;

    private Cell[][] neighbors;

    public Cell[][] getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Cell[][] neighbors) {
        this.neighbors = neighbors;
    }

    public GameOfLifeCellState getCellState() {
        return cellState;
    }

    public void setCellState(GameOfLifeCellState cellState) {
        this.cellState = cellState;
    }
}
