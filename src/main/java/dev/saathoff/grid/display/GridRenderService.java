package dev.saathoff.grid.display;

import dev.saathoff.game.data.Cell;
import dev.saathoff.grid.data.Grid;

public interface GridRenderService<T extends Cell> {
    String renderGrid(Grid<T> grid);
}
