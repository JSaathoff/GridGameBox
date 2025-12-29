package dev.saathoff.gameoflife.interaction;

import dev.saathoff.gameoflife.bean.GOLCell;
import dev.saathoff.grid.bean.Grid;
import dev.saathoff.grid.interaction.CellInteraction;

public class GOLToggleCellInteraction implements CellInteraction<GOLCell> {
    @Override
    public void interact(Grid<GOLCell> grid, int row, int column) {
        GOLCell cell = grid.getCell(row,column);
        cell.setAlive(!cell.isAlive());
    }
}
