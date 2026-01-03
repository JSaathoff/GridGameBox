package dev.saathoff.minesweeper.service;

import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.grid.service.DetermineNeighborsService;
import dev.saathoff.minesweeper.bean.MSCell;

import java.util.List;

public class RevealCellService {

    private DetermineNeighborsService neighborsService;

    public RevealCellService(DetermineNeighborsService neighborsService) {
        this.neighborsService = neighborsService;
    }

    public void revealCell(Grid<MSCell> grid, int row, int column) {
        MSCell cell = grid.getCell(row, column);
        if (cell.isRevealed() || cell.isFlagged()) {
            return;
        }
        cell.setRevealed(true);
        if(cell.getMineCount() == 0) {
            List<Coordinate> coordinates = neighborsService.determineNeighborCoordinates(grid, row, column);
            for(Coordinate coordinate : coordinates){
                this.revealCell(grid, coordinate.row(), coordinate.column());
            }
        }
    }

    public DetermineNeighborsService getNeighborsService() {
        return neighborsService;
    }

    public void setNeighborsService(DetermineNeighborsService neighborsService) {
        this.neighborsService = neighborsService;
    }
}
