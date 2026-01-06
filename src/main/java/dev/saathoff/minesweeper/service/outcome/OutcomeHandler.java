package dev.saathoff.minesweeper.service.outcome;

import dev.saathoff.grid.data.Grid;
import dev.saathoff.io.output.OutputService;
import dev.saathoff.minesweeper.bean.MSCell;
import dev.saathoff.minesweeper.bean.MSGameState;
import dev.saathoff.minesweeper.display.MSDisplayService;

public interface OutcomeHandler {
    void handleOutcome(MSGameState gameState, Grid<MSCell> grid);
}
