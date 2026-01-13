package dev.saathoff.minesweeper.service;

import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.grid.service.DetermineNeighborsService;
import dev.saathoff.minesweeper.data.MSCell;
import dev.saathoff.minesweeper.data.MSGameState;

import java.util.List;
import java.util.stream.Stream;

public class RevealCellService {

    private DetermineNeighborsService neighborsService;

    public RevealCellService(DetermineNeighborsService neighborsService) {
        this.neighborsService = neighborsService;
    }

    public void revealCell(MSGameState gameState, Grid<MSCell> grid, int row, int column) {
        MSCell cell = grid.getCell(row, column);
        if (cell.isRevealed() || cell.isFlagged()) {
            return;
        }
        cell.setRevealed(true);
        int revealedCellCount = gameState.getRevealedCellCount();
        gameState.setRevealedCellCount(++revealedCellCount);
        if (cell.getMineCount() == 0) {
            List<Coordinate> coordinates = neighborsService.determineNeighborCoordinates(grid, row, column);
            for (Coordinate coordinate : coordinates) {
                this.revealCell(gameState, grid, coordinate.row(), coordinate.column());
            }
        }
    }

    public void revealNumber(MSGameState gameState, Grid<MSCell> grid, int row, int column) {
        MSCell cell = grid.getCell(row, column);
        List<Coordinate> neighborCoordinates = neighborsService.determineNeighborCoordinates(grid, row, column);
        neighborCoordinates.forEach(coordinate -> revealCell(gameState, grid, coordinate.row(), coordinate.column()));
    }

    public boolean isNumberRevealable(Grid<MSCell> grid, int row, int column) {
        MSCell cell = grid.getCell(row, column);
        long mineCount = cell.getMineCount();
        List<Coordinate> neighborCoordinates = neighborsService.determineNeighborCoordinates(grid, row, column);
        Stream<MSCell> msCellStream = neighborCoordinates.stream().map(grid::getCell);
        long flaggedCount = msCellStream.filter(MSCell::isFlagged).count();
        return flaggedCount == mineCount;
    }

    public DetermineNeighborsService getNeighborsService() {
        return neighborsService;
    }

    public void setNeighborsService(DetermineNeighborsService neighborsService) {
        this.neighborsService = neighborsService;
    }


}
