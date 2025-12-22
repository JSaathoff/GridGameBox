package dev.saathoff;

import dev.saathoff.bean.Grid;
import dev.saathoff.service.CellStateCalculationService;
import dev.saathoff.service.GridService;
import dev.saathoff.service.display.GridDisplayService;
import dev.saathoff.service.display.impl.GOLDisplayService;
import dev.saathoff.service.interaction.CellInteraction;
import dev.saathoff.service.interaction.impl.GOLToggleCellInteraction;

public class Main {
    public static void main(String[] args) {

        GridService gridService = new GridService();

        Grid grid = gridService.generateNewGrid(5, 5);
        GridDisplayService displayService = new GOLDisplayService();

        CellInteraction toggleCellService = new GOLToggleCellInteraction();

        toggleCellService.interact(grid, 2,1);
        toggleCellService.interact(grid, 2,2);
        toggleCellService.interact(grid, 2,3);

        CellStateCalculationService cellStateCalculationService = new CellStateCalculationService();
        System.out.println(displayService.displayGridState(grid));

        Grid nextGridState = gridService.calculateNextGridState(grid, cellStateCalculationService);
        System.out.println(displayService.displayGridState(nextGridState));
    }
}