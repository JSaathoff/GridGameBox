package dev.saathoff.grid.data;

import dev.saathoff.game.data.Cell;

import java.util.Arrays;
import java.util.stream.Stream;

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
    public Stream<T> asStream() {
        return Arrays.stream(this.cells)
                .flatMap(Arrays::stream)
                .map(obj -> (T) obj);
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
        if(row < 0 || row >= this.getRowCount()){
            throw new IndexOutOfBoundsException("Row " + row + " is out of bounds for height " + getRowCount());
        }
        if(column < 0 || column >= this.getColumnCount()){
            throw new IndexOutOfBoundsException("Column " + column + " is out of bounds for width " + getColumnCount());
        }
    }
}
