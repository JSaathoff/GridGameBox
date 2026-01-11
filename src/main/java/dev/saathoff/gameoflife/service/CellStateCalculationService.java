package dev.saathoff.gameoflife.service;

import dev.saathoff.gameoflife.data.GOLCell;
import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.grid.service.DetermineNeighborsService;

import java.util.List;

public class CellStateCalculationService {

    private DetermineNeighborsService determineNeighborsService;
    private GOLGridInitService gridInitService;

    public CellStateCalculationService(DetermineNeighborsService determineNeighborsService, GOLGridInitService gridInitService) {
        this.determineNeighborsService = determineNeighborsService;
        this.gridInitService = gridInitService;
    }

    public Grid<GOLCell> calculateNextGridState(Grid<GOLCell> grid, CellStateCalculationService cellStateCalculationService) {
        Grid<GOLCell> nextGenGrid = this.gridInitService.generateNewGrid(grid.getRowCount(), grid.getColumnCount());

        for (int i = 0; i < grid.getRowCount(); i++) {
            for (int j = 0; j < grid.getColumnCount(); j++) {
                GOLCell nextGenCell = this.calculateCellState(grid, i, j);
                nextGenGrid.setCell(i, j, nextGenCell);
            }
        }
        return nextGenGrid;
    }

    public GOLCell calculateCellState(Grid<GOLCell> grid, int row, int column) {
        GOLCell cell = grid.getCell(row, column);
        GOLCell nextGenCell = this.copyCell(cell);
        int aliveNeighbors = this.countAliveNeighbors(grid, row, column);
        if (cell.isAlive() && (aliveNeighbors < 2 || aliveNeighbors > 3)) {
            nextGenCell.setAlive(false);
        }
        if (!cell.isAlive() && aliveNeighbors == 3) {
            nextGenCell.setAlive(true);
        }
        return nextGenCell;
    }

    private GOLCell copyCell(GOLCell cell) {
        GOLCell nextGenCell = new GOLCell();
        nextGenCell.setAlive(cell.isAlive());
        return nextGenCell;
    }

    private int countAliveNeighbors(Grid<GOLCell> grid, int row, int column) {

        int aliveNeighbors = 0;

        List<Coordinate> neighborCoordinates = this.determineNeighborsService.determineNeighborCoordinates(grid, row, column);

        for (Coordinate cord : neighborCoordinates) {
            GOLCell neighborCell = grid.getCell(cord);
            if (neighborCell.isAlive()) {
                aliveNeighbors++;
            }
        }

        return aliveNeighbors;
    }


    public DetermineNeighborsService getDetermineNeighborsService() {
        return determineNeighborsService;
    }

    public void setDetermineNeighborsService(DetermineNeighborsService determineNeighborsService) {
        this.determineNeighborsService = determineNeighborsService;
    }
}
