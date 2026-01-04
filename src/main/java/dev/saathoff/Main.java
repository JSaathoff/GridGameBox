package dev.saathoff;

import dev.saathoff.gameoflife.data.GOLCell;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.gameoflife.service.CellStateCalculationService;
import dev.saathoff.gameoflife.service.GOLAbstractGridService;
import dev.saathoff.grid.display.GridDisplayService;
import dev.saathoff.gameoflife.display.GOLDisplayService;
import dev.saathoff.game.interaction.CellInteraction;
import dev.saathoff.gameoflife.interaction.GOLToggleCellInteraction;

public class Main {
    public static void main(String[] args) {

        GOLAbstractGridService gridService = new GOLAbstractGridService(new CellStateCalculationService());
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