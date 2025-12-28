package dev.saathoff.service.minesweeper;

import dev.saathoff.bean.Grid;
import dev.saathoff.bean.MSCell;
import dev.saathoff.service.grid.dto.Coordinates;
import dev.saathoff.service.grid.impl.DetermineNeighborsService;

import java.util.List;

public class MineCountCalculator {

    private DetermineNeighborsService neighborsService;

    public MineCountCalculator(DetermineNeighborsService neighborsService) {
        this.neighborsService = neighborsService;
    }

    public Grid<MSCell> determineMineCountForGrid(Grid<MSCell> grid){
        for(int row = 0; row < grid.getRowCount(); row++)      {
            for(int col = 0; col < grid.getColumnCount(); col++){
                determineMineCountForCell(grid, row, col);
            }
        }
        return grid;
    }

    public void determineMineCountForCell(Grid<MSCell> grid, int row, int col) {
        MSCell currentCell = grid.getCell(row, col);
        List<Coordinates> neighbors = neighborsService.determineNeighborCoordinates(grid, row, col);
        long mineCount = neighbors.stream()
                .map(coordinates -> grid.getCell(coordinates))
                .filter(cell -> cell.isMine())
                .count();
        currentCell.setMineCount(mineCount);
    }

}
