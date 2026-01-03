package dev.saathoff.grid.display;

import dev.saathoff.game.data.Cell;
import dev.saathoff.grid.data.Grid;

public interface GridDisplayService<T extends Cell> {
    String displayGridState(Grid<T> grid);
}
