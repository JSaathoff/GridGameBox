package dev.saathoff.minesweeper.service.outcome;

import dev.saathoff.grid.data.Grid;
import dev.saathoff.grid.display.GridRenderService;
import dev.saathoff.io.output.OutputService;
import dev.saathoff.minesweeper.bean.MSCell;
import dev.saathoff.minesweeper.bean.MSGameState;

public class LoseOutcomeHandler implements OutcomeHandler {

    private GridRenderService<MSCell> displayService;

    private OutputService outputService;

    public LoseOutcomeHandler(GridRenderService<MSCell> displayService, OutputService outputService) {
        this.displayService = displayService;
        this.outputService = outputService;
    }

    @Override
    public void handleOutcome(MSGameState gameState, Grid<MSCell> grid) {
        revealCells(grid);
        String gridDisplayString = displayService.renderGrid(grid);
        outputService.output(gridDisplayString);
        outputService.output("Noooo! you lost :(");
        outputService.output("You placed " + gameState.getFlagCount() + " flags");
    }

    private void revealCells(Grid<MSCell> grid) {
        for (int row = 0; row < grid.getRowCount(); row++) {
            for (int column = 0; column < grid.getColumnCount(); column++) {
                MSCell cell = grid.getCell(row, column);
                cell.setRevealed(true);
            }
        }
    }
}
