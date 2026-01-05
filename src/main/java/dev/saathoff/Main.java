package dev.saathoff;

import dev.saathoff.grid.service.DetermineNeighborsService;
import dev.saathoff.io.input.ConsoleCoordinateInput;
import dev.saathoff.io.input.select.ConsoleSelectInput;
import dev.saathoff.io.output.ConsoleOutputService;
import dev.saathoff.minesweeper.display.MSDisplayService;
import dev.saathoff.minesweeper.gameloop.MinesweeperRunner;
import dev.saathoff.minesweeper.interaction.RevealAllNeighborsOfRevealedCellInteraction;
import dev.saathoff.minesweeper.interaction.RevealInteraction;
import dev.saathoff.minesweeper.interaction.ToggleFlagInteraction;
import dev.saathoff.minesweeper.service.MSGridService;
import dev.saathoff.minesweeper.service.MineCountCalculator;
import dev.saathoff.minesweeper.service.MineService;
import dev.saathoff.minesweeper.service.RevealCellService;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        GOLAbstractGridService gridService = new GOLAbstractGridService(new CellStateCalculationService());
        GridDisplayService displayService = new GOLDisplayService();
        CellInteraction toggleCellInteraction = new GOLToggleCellInteraction();
        GOLGameState state = new GOLGameState();

        Grid<GOLCell> grid = gridService.generateNewGrid(5, 5);

        toggleCellInteraction.interact(state, grid, 2,1);
        toggleCellInteraction.interact(state, grid, 2,2);
        toggleCellInteraction.interact(state, grid, 2,3);

        CellStateCalculationService cellStateCalculationService = new CellStateCalculationService();
        System.out.println(displayService.displayGridState(grid));

        Grid<GOLCell> nextGridState = gridService.calculateNextGridState(grid, cellStateCalculationService);
        System.out.println(displayService.displayGridState(nextGridState));

         */
        RevealInteraction revealInteraction = new RevealInteraction(new RevealCellService(new DetermineNeighborsService()));
        MinesweeperRunner minesweeper = new MinesweeperRunner(
                new MSGridService(),
                new MineService(),
                new MineCountCalculator(new DetermineNeighborsService()),
                new HashMap<>(),
                revealInteraction,
                new ConsoleSelectInput(new Scanner(System.in)),
                new ConsoleCoordinateInput(new Scanner(System.in)),
                new MSDisplayService(),
                new ConsoleOutputService()
        );
        minesweeper.registerInteractions(
                revealInteraction,
                new ToggleFlagInteraction(),
                new RevealAllNeighborsOfRevealedCellInteraction()
        );
        minesweeper.runGame();
    }
}