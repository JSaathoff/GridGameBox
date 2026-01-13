package dev.saathoff.minesweeper.service;

import dev.saathoff.grid.service.AbstractGridInitService;
import dev.saathoff.minesweeper.data.MSCell;

public class MSGridInitService extends AbstractGridInitService<MSCell> {

    @Override
    public MSCell initializeCell() {
        MSCell cell = new MSCell();
        return cell;
    }


}
