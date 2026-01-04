package dev.saathoff.game.interaction;

import dev.saathoff.game.data.Cell;
import dev.saathoff.grid.data.Grid;

public interface CellInteraction<T extends Cell> {
     void interact(Grid<T> grid , int row, int column);
}
