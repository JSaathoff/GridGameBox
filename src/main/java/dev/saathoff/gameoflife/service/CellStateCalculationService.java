package dev.saathoff.gameoflife.service;

import dev.saathoff.gameoflife.bean.GOLCell;
import dev.saathoff.grid.bean.Grid;
import dev.saathoff.grid.bean.Coordinates;
import dev.saathoff.grid.service.DetermineNeighborsService;

import java.util.List;

public class CellStateCalculationService {

    private DetermineNeighborsService determineNeighborsService;

    private int countAliveNeighbors(Grid<GOLCell> grid, int row, int column) {

        int aliveNeighbors = 0;

        List<Coordinates> neighborCoordinates = this.determineNeighborsService.determineNeighborCoordinates(grid, row, column);

        for(Coordinates cord : neighborCoordinates){
            GOLCell neighborCell = grid.getCell(cord);
            if(neighborCell.isAlive()){
                aliveNeighbors++;
            }
        }

        return aliveNeighbors;
    }

    public GOLCell calculateCellState(Grid<GOLCell> grid, int row, int column) {
        GOLCell cell = grid.getCell(row, column);
        GOLCell nextGenCell = this.copyCell(cell);
        int aliveNeighbors = this.countAliveNeighbors(grid, row, column);
        if(cell.isAlive() && (aliveNeighbors < 2 || aliveNeighbors > 3)){
                nextGenCell.setAlive(false);
        }
        if(!cell.isAlive() && aliveNeighbors == 3){
            nextGenCell.setAlive(true);
        }
        return nextGenCell;
    }

    private GOLCell copyCell(GOLCell cell) {
        GOLCell nextGenCell = new GOLCell();
        nextGenCell.setAlive(cell.isAlive());
        return nextGenCell;
    }

    public DetermineNeighborsService getDetermineNeighborsService() {
        return determineNeighborsService;
    }

    public void setDetermineNeighborsService(DetermineNeighborsService determineNeighborsService) {
        this.determineNeighborsService = determineNeighborsService;
    }
}
