package dev.saathoff.service.interaction.impl;

import dev.saathoff.bean.Cell;
import dev.saathoff.bean.Grid;
import dev.saathoff.service.interaction.CellInteraction;

public class GOLToggleCellInteraction implements CellInteraction {
    @Override
    public void interact(Grid grid, int row, int column) {
        Cell cell = grid.getGrid().get(row).get(column);
        cell.setAlive(!cell.isAlive());
    }
}
