package dev.saathoff.gameoflife.interaction;

import dev.saathoff.game.data.AbstractGameState;
import dev.saathoff.gameoflife.data.GOLCell;
import dev.saathoff.gameoflife.data.GOLMetaData;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.game.interaction.CellInteraction;

public class GOLToggleCellInteraction implements CellInteraction<GOLCell, GOLMetaData> {


    @Override
    public void interact(AbstractGameState<GOLCell, GOLMetaData> gameState, int row, int column) {
        Grid<GOLCell> grid = gameState.getGrid();
        GOLCell cell = grid.getCell(row,column);
        cell.setAlive(!cell.isAlive());
    }
}
