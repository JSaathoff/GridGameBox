package dev.saathoff;

import dev.saathoff.bean.GOLCell;
import dev.saathoff.bean.Grid;
import dev.saathoff.service.CellStateCalculationService;
import dev.saathoff.service.grid.impl.GOLGridService;
import dev.saathoff.service.display.GridDisplayService;
import dev.saathoff.service.display.impl.GOLDisplayService;
import dev.saathoff.service.interaction.CellInteraction;
import dev.saathoff.service.interaction.impl.GOLToggleCellInteraction;

public class Main {
    public static void main(String[] args) {

        GOLGridService gridService = new GOLGridService(new CellStateCalculationService());
        GridDisplayService displayService = new GOLDisplayService();
        CellInteraction toggleCellInteraction = new GOLToggleCellInteraction();

        Grid<GOLCell> grid = gridService.generateNewGrid(5, 5);


        toggleCellInteraction.interact(grid, 2,1);
        toggleCellInteraction.interact(grid, 2,2);
        toggleCellInteraction.interact(grid, 2,3);

        CellStateCalculationService cellStateCalculationService = new CellStateCalculationService();
        System.out.println(displayService.displayGridState(grid));

        Grid<GOLCell> nextGridState = gridService.calculateNextGridState(grid, cellStateCalculationService);
        System.out.println(displayService.displayGridState(nextGridState));
    }
}