package dev.saathoff.gameoflife.service;

import dev.saathoff.gameoflife.bean.GOLCell;
import dev.saathoff.grid.bean.Grid;
import dev.saathoff.grid.service.AbstractGridService;


public class GOLAbstractGridService extends AbstractGridService<GOLCell> {

    public GOLAbstractGridService(CellStateCalculationService service) {
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
