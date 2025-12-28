package dev.saathoff.service.grid.impl;

import dev.saathoff.bean.GOLCell;
import dev.saathoff.bean.Grid;
import dev.saathoff.service.CellStateCalculationService;
import dev.saathoff.service.grid.GridService;


public class GOLGridService extends GridService<GOLCell> {

    public GOLGridService(CellStateCalculationService service) {
        this.service = service;
    }

    private CellStateCalculationService service;

    @Override
    public GOLCell initializeCell() {
        GOLCell cell = new GOLCell();
        cell.setAlive(false);
        return cell;
    }

    @Override
    public GOLCell nextGenerationState(Grid<GOLCell> grid, int row, int column) {
        return this.service.calculateCellState(grid, row, column);
    }
}
