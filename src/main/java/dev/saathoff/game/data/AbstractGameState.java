package dev.saathoff.game.data;

import dev.saathoff.grid.data.Grid;

public abstract class AbstractGameState<C extends Cell, T> {

    private Grid<C> grid;

    private T metaData;

    public Grid<C> getGrid() {
        return grid;
    }

    public void setGrid(Grid<C> grid) {
        this.grid = grid;
    }

    public T getMetaData() {
        return metaData;
    }

    public void setMetaData(T metaData) {
        this.metaData = metaData;
    }
}
