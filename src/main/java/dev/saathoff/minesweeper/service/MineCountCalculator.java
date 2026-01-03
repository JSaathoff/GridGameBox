package dev.saathoff.minesweeper.service;

import dev.saathoff.grid.data.Grid;
import dev.saathoff.minesweeper.bean.MSCell;
import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.grid.service.DetermineNeighborsService;

import java.util.List;

public class MineCountCalculator {

    private DetermineNeighborsService neighborsService;

    public MineCountCalculator(DetermineNeighborsService neighborsService) {
        this.neighborsService = neighborsService;
    }

    public Grid<MSCell> setMineCountsForGrid(Grid<MSCell> grid){
        for(int row = 0; row < grid.getRowCount(); row++)      {
            for(int col = 0; col < grid.getColumnCount(); col++){
                setMineCountForCell(grid, row, col);
            }
        }
        return grid;
    }

    public void setMineCountForCell(Grid<MSCell> grid, int row, int col) {
        MSCell currentCell = grid.getCell(row, col);
        List<Coordinate> neighbors = neighborsService.determineNeighborCoordinates(grid, row, col);
        long mineCount = neighbors.stream()
                .map(coordinates -> grid.getCell(coordinates))
                .filter(cell -> cell.isMine())
                .count();
        currentCell.setMineCount(mineCount);
    }

}
