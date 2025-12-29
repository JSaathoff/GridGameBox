package dev.saathoff.grid.interaction;

import dev.saathoff.grid.bean.Grid;

public interface CellInteraction<T> {
     void interact(Grid<T> grid, int row, int column);
}
