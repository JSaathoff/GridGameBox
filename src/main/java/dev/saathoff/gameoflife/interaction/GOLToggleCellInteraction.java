package dev.saathoff.gameoflife.interaction;

import dev.saathoff.gameoflife.data.GOLCell;
import dev.saathoff.gameoflife.data.GOLGameState;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.game.interaction.CellInteraction;

public class GOLToggleCellInteraction implements CellInteraction<GOLCell, GOLGameState> {


    @Override
    public void interact(GOLGameState gameState, Grid<GOLCell> grid, int row, int column) {
        GOLCell cell = grid.getCell(row,column);
        cell.setAlive(!cell.isAlive());
    }


    @Override
    public String getLabelForSelection() {
        return "Toggle Cell";
    }
}
