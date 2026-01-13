package dev.saathoff.minesweeper.interaction;

import dev.saathoff.grid.data.Cell;
import dev.saathoff.grid.data.GameState;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.io.input.select.Selectable;
import dev.saathoff.minesweeper.data.exception.IllegalMoveException;

public interface CellInteraction<C extends Cell, G extends GameState> extends Selectable {
    void interact(G gameState, Grid<C> grid, int row, int column) throws IllegalMoveException;
}
