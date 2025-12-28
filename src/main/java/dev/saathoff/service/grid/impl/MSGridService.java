package dev.saathoff.service.grid.impl;

import dev.saathoff.bean.Grid;
import dev.saathoff.bean.MSCell;
import dev.saathoff.service.grid.GridService;

public class MSGridService extends GridService<MSCell> {

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
