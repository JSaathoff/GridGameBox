package dev.saathoff.minesweeper.interaction;

import dev.saathoff.grid.bean.Grid;
import dev.saathoff.minesweeper.bean.MSCell;
import dev.saathoff.grid.interaction.CellInteraction;

public class RevealInteraction implements CellInteraction<MSCell> {

    @Override
    public void interact(Grid<MSCell> grid, int row, int column) {
        MSCell cell = grid.getCell(row, column);
        if(cell.isMine()){
            //lost
        }
        // should place mines if used the first time
    }

}
