package dev.saathoff.minesweeper.service.outcome;

import dev.saathoff.grid.data.Grid;
import dev.saathoff.grid.display.GridDisplayService;
import dev.saathoff.io.output.OutputService;
import dev.saathoff.minesweeper.bean.MSCell;
import dev.saathoff.minesweeper.bean.MSGameState;

public class WonOutcomeHandler implements OutcomeHandler{
    private GridDisplayService<MSCell> displayService;

    private OutputService outputService;

    public WonOutcomeHandler(GridDisplayService<MSCell> displayService, OutputService outputService) {
        this.displayService = displayService;
        this.outputService = outputService;
    }
    @Override
    public void handleOutcome(MSGameState gameState, Grid<MSCell> grid) {
        outputService.output(displayService.displayGridState(grid));
        outputService.output("Yeah you won!");
        // TODO output playtime once we implemented that :D
    }
}
