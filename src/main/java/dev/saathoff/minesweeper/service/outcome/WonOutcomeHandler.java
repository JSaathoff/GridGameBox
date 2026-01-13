package dev.saathoff.minesweeper.service.outcome;

import dev.saathoff.grid.data.Grid;
import dev.saathoff.grid.display.GridRenderService;
import dev.saathoff.io.output.OutputService;
import dev.saathoff.minesweeper.data.MSCell;
import dev.saathoff.minesweeper.data.MSGameState;

public class WonOutcomeHandler implements OutcomeHandler {
    private GridRenderService<MSCell> displayService;

    private OutputService outputService;

    public WonOutcomeHandler(GridRenderService<MSCell> displayService, OutputService outputService) {
        this.displayService = displayService;
        this.outputService = outputService;
    }

    @Override
    public void handleOutcome(MSGameState gameState, Grid<MSCell> grid) {
        outputService.output(displayService.renderGrid(grid));
        outputService.output("Yeah you won!");
        // TODO output playtime once we implemented that :D
    }
}
