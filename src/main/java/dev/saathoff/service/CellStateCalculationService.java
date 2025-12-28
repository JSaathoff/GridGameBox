package dev.saathoff.service;

import dev.saathoff.bean.GOLCell;
import dev.saathoff.bean.Grid;
import dev.saathoff.service.grid.dto.Coordinates;
import dev.saathoff.service.grid.impl.DetermineNeighborsService;

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
