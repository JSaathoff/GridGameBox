package dev.saathoff.grid.data;

import dev.saathoff.game.data.Cell;

public class ArrayGrid<T extends Cell> implements Grid<T> {

    private Object[][] cells;

    public ArrayGrid(int rows, int columns) {
        this.cells = new Object[rows][columns];
    }

    @Override
    public void setCell( int row, int column, T cell){
        this.checkBoundaries(row, column);
        this.cells[row][column] = cell;
    }

    @Override
    public T getCell(int row, int column){
        this.checkBoundaries(row, column);
        return (T) this.cells[row][column];
    }

    @Override
    public T getCell(Coordinate cord) {
        return this.getCell(cord.row(), cord.column());
    }

    @Override
    public int getRowCount() {
        return this.cells.length;
    }
    @Override
    public int getColumnCount(){
        return this.cells[0].length;
    }

    private void checkBoundaries(int row, int column){
        if(row < 0 && row >= this.cells.length){
            throw new IndexOutOfBoundsException("Row does not exist");
        }
        if(column < 0 && row >= this.cells[0].length){
            throw new IndexOutOfBoundsException("Column does not exist");
        }
    }
}
