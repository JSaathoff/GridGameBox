package dev.saathoff;

import dev.saathoff.bean.Grid;
import dev.saathoff.bean.GameOfLifeCellState;
import dev.saathoff.service.CellStateCalculationService;
import dev.saathoff.service.GridService;

public class Main {
    public static void main(String[] args) {

        GridService gridService = new GridService();

        Grid grid = gridService.generateNewGrid(5, 5);

        grid.getGrid().get(2).get(1).setCellState(GameOfLifeCellState.ALIVE);
        grid.getGrid().get(2).get(2).setCellState(GameOfLifeCellState.ALIVE);
        grid.getGrid().get(2).get(3).setCellState(GameOfLifeCellState.ALIVE);


        CellStateCalculationService cellStateCalculationService = new CellStateCalculationService();
        System.out.println(gridService.displayGridState(grid));

        Grid nextGridState = gridService.calculateNextGridState(grid, cellStateCalculationService);
        System.out.println(gridService.displayGridState(nextGridState));
    }
}