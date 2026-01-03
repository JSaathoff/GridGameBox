package dev.saathoff.minesweeper.service;

import dev.saathoff.grid.data.Grid;
import dev.saathoff.minesweeper.bean.MSCell;
import dev.saathoff.grid.service.AbstractGridService;

public class MSGridService extends AbstractGridService<MSCell> {

    @Override
    public MSCell initializeCell() {
        MSCell cell = new MSCell();
        return cell;
    }

    @Override
    public MSCell nextGenerationState(Grid<MSCell> grid, int row, int column) {
        return null;
    }


}
