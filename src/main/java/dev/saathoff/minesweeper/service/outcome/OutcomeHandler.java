package dev.saathoff.minesweeper.service.outcome;

import dev.saathoff.grid.data.Grid;
import dev.saathoff.minesweeper.data.MSCell;
import dev.saathoff.minesweeper.data.MSGameState;

public interface OutcomeHandler {
    void handleOutcome(MSGameState gameState, Grid<MSCell> grid);
}
