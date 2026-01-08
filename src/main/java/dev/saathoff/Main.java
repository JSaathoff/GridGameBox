package dev.saathoff;

import dev.saathoff.gameoflife.display.GOLRenderService;
import dev.saathoff.gameoflife.gameloop.GameOfLifeRunner;
import dev.saathoff.gameoflife.service.CellStateCalculationService;
import dev.saathoff.gameoflife.service.GOLAbstractGridService;
import dev.saathoff.grid.service.DetermineNeighborsService;
import dev.saathoff.io.input.ConsoleInputSource;
import dev.saathoff.io.input.converter.impl.CoordinateConverter;
import dev.saathoff.io.input.impl.CancellableCoordinateInput;
import dev.saathoff.io.input.impl.IntegerInput;
import dev.saathoff.io.input.validator.impl.CoordinateValidator;
import dev.saathoff.io.output.ConsoleOutputService;

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

        CancellableCoordinateInput cancellableCoordinateInput = new CancellableCoordinateInput(outputService, inputSource, new CoordinateConverter(), new CoordinateValidator());
        //Optional<Coordinate> coordinate1 = cancellableCoordinateInput.getInput("Test, cancel by entering q", new CoordinateValidationCriteria(1, 3, 1, 3));
        //System.out.println(coordinate1);
        IntegerInput integerInput = new IntegerInput(outputService, inputSource);
        //int result = integerInput.getInput("Test", new RangeValidationCriteria(0, 5));
        //System.out.println(result);


        //CoordinateInput coordinateInput = new CoordinateInput(outputService, inputSource);
        // Coordinate coordinate = coordinateInput.getInput("Coordinates:", new CoordinateValidationCriteria(0, 5, 0, 5));
        //System.out.println(coordinate);
    /*
        SelectInputImpl consoleSelectInput = new SelectInputImpl(outputService, new NumberSelectionInput(outputService, inputSource));
        Difficulty difficulty = consoleSelectInput.selectFromEnum("Test", Difficulty.class);
        System.out.println(difficulty);
*/
        DetermineNeighborsService determineNeighborsService = new DetermineNeighborsService();
        CellStateCalculationService cellStateCalculationService = new CellStateCalculationService(determineNeighborsService);
        GOLAbstractGridService gridService = new GOLAbstractGridService(cellStateCalculationService);
        GameOfLifeRunner gameOfLife = new GameOfLifeRunner(integerInput, outputService, gridService, cancellableCoordinateInput, new GOLRenderService(), cellStateCalculationService);
        gameOfLife.runGame();
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