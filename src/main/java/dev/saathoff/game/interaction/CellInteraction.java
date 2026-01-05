package dev.saathoff.game.interaction;

import dev.saathoff.game.data.Cell;
import dev.saathoff.game.data.GameState;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.io.select.Selectable;

public interface CellInteraction<C extends Cell,G extends GameState> extends Selectable {
     void interact(G gameState, Grid<C> grid , int row, int column);
}
