package dev.saathoff.service.interaction;

import dev.saathoff.bean.Grid;

public interface CellInteraction<T> {
     void interact(Grid<T> grid, int row, int column);
}
