package dev.saathoff;

import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.io.input.ConsoleInputSource;
import dev.saathoff.io.input.NumberSelectionInput;
import dev.saathoff.io.input.impl.CoordinateInput;
import dev.saathoff.io.input.impl.IntegerInput;
import dev.saathoff.io.input.select.SelectInputImpl;
import dev.saathoff.io.input.validator.criteria.CoordinateValidationCriteria;
import dev.saathoff.io.input.validator.criteria.IntegerValidationCriteria;
import dev.saathoff.io.output.ConsoleOutputService;
import dev.saathoff.minesweeper.bean.Difficulty;

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
        ConsoleOutputService outputService = new ConsoleOutputService();
        ConsoleInputSource inputSource = new ConsoleInputSource(new Scanner(System.in));
        IntegerInput integerInput = new IntegerInput(outputService, inputSource);
        int result = integerInput.getInput("Test", new IntegerValidationCriteria(0, 5));
        System.out.println(result);

        CoordinateInput coordinateInput = new CoordinateInput(outputService, inputSource);
        Coordinate coordinate = coordinateInput.getInput("Coordinates:", new CoordinateValidationCriteria(0, 5, 0, 5));
        System.out.println(coordinate);

        SelectInputImpl consoleSelectInput = new SelectInputImpl(outputService, new NumberSelectionInput(outputService, inputSource));
        Difficulty difficulty = consoleSelectInput.selectFromEnum("Test", Difficulty.class);
        System.out.println(difficulty);

        /*
        RevealInteraction revealInteraction = new RevealInteraction(new RevealCellService(new DetermineNeighborsService()));
        OutputService outputService = new ConsoleOutputService();
        MinesweeperRunner minesweeper = new MinesweeperRunner(
                new MSGridService(),
                new MineService(),
                new MineCountCalculator(new DetermineNeighborsService()),
                new HashMap<>(),
                revealInteraction,
                new ConsoleSelectInput(new Scanner(System.in), outputService),
                new ConsoleCoordinateInput(new Scanner(System.in), outputService),
                new MSDisplayService(),
                outputService
        );
        minesweeper.registerInteractions(
                revealInteraction,
                new ToggleFlagInteraction(),
                new RevealAllNeighborsOfRevealedCellInteraction()
        );
        minesweeper.runGame();

         */
    }
}