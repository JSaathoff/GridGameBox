package dev.saathoff.service.interaction.impl;

import dev.saathoff.bean.GOLCell;
import dev.saathoff.bean.Grid;
import dev.saathoff.service.interaction.CellInteraction;

public class GOLToggleCellInteraction implements CellInteraction<GOLCell> {
    @Override
    public void interact(Grid<GOLCell> grid, int row, int column) {
        GOLCell cell = grid.getCell(row,column);
        cell.setAlive(!cell.isAlive());
    }
}
