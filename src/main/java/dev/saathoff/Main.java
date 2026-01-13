package dev.saathoff;

import dev.saathoff.gamebox.GameboxRunner;
import dev.saathoff.gamebox.RunnableGame;
import dev.saathoff.gameoflife.display.GOLRenderService;
import dev.saathoff.gameoflife.gameloop.GameOfLifeRunner;
import dev.saathoff.gameoflife.service.CellStateCalculationService;
import dev.saathoff.gameoflife.service.GOLGridInitService;
import dev.saathoff.grid.service.DetermineNeighborsService;
import dev.saathoff.io.input.ConsoleInputSource;
import dev.saathoff.io.input.NumberSelectionInput;
import dev.saathoff.io.input.converter.impl.CoordinateConverter;
import dev.saathoff.io.input.converter.impl.IntegerInputConverter;
import dev.saathoff.io.input.impl.CancellableCoordinateInput;
import dev.saathoff.io.input.impl.CancellableIntegerSelectionInput;
import dev.saathoff.io.input.impl.CoordinateInput;
import dev.saathoff.io.input.impl.IntegerInput;
import dev.saathoff.io.input.select.CancellableSelectInput;
import dev.saathoff.io.input.select.CancellableSelectInputImpl;
import dev.saathoff.io.input.select.SelectInput;
import dev.saathoff.io.input.select.SelectInputImpl;
import dev.saathoff.io.input.validator.impl.ContainedInCollectionValidator;
import dev.saathoff.io.input.validator.impl.CoordinateValidator;
import dev.saathoff.io.output.ConsoleOutputService;
import dev.saathoff.minesweeper.data.MSCell;
import dev.saathoff.minesweeper.data.MSGameState;
import dev.saathoff.minesweeper.display.MSRenderService;
import dev.saathoff.minesweeper.gameloop.MinesweeperRunner;
import dev.saathoff.minesweeper.interaction.CellInteraction;
import dev.saathoff.minesweeper.interaction.impl.RevealInteraction;
import dev.saathoff.minesweeper.interaction.impl.ToggleFlagInteraction;
import dev.saathoff.minesweeper.service.MSGridInitService;
import dev.saathoff.minesweeper.service.MineCountCalculator;
import dev.saathoff.minesweeper.service.MineService;
import dev.saathoff.minesweeper.service.RevealCellService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // General configuration
        ConsoleOutputService outputService = new ConsoleOutputService();
        ConsoleInputSource inputSource = new ConsoleInputSource(new Scanner(System.in));
        DetermineNeighborsService determineNeighborsService = new DetermineNeighborsService();

        GameOfLifeRunner gameOfLife = configureGameOfLifeRunner(outputService, inputSource, determineNeighborsService);

        MinesweeperRunner minesweeper = configureMinesweeperRunner(outputService, inputSource, determineNeighborsService);

        GameboxRunner gameboxRunner = configureGameboxRunner(outputService, minesweeper, gameOfLife, inputSource);

        gameboxRunner.runGamebox();

    }

    private static GameboxRunner configureGameboxRunner(ConsoleOutputService outputService, MinesweeperRunner minesweeper, GameOfLifeRunner gameOfLife, ConsoleInputSource inputSource) {
        CancellableSelectInput gameSelect = new CancellableSelectInputImpl(outputService, new CancellableIntegerSelectionInput(outputService, inputSource, new IntegerInputConverter(), new ContainedInCollectionValidator()));
        Map<Integer, RunnableGame> games = new HashMap<>();
        games.put(1, minesweeper);
        games.put(2, gameOfLife);
        GameboxRunner gameboxRunner = new GameboxRunner(outputService, games, gameSelect);
        return gameboxRunner;
    }

    private static GameOfLifeRunner configureGameOfLifeRunner(ConsoleOutputService outputService, ConsoleInputSource inputSource, DetermineNeighborsService determineNeighborsService) {
        CancellableCoordinateInput cancellableCoordinateInput = new CancellableCoordinateInput(outputService, inputSource, new CoordinateConverter(), new CoordinateValidator());

        IntegerInput integerInput = new IntegerInput(outputService, inputSource);
        GOLGridInitService gridService = new GOLGridInitService();
        CellStateCalculationService cellStateCalculationService = new CellStateCalculationService(determineNeighborsService, gridService);

        GameOfLifeRunner gameOfLife = new GameOfLifeRunner(integerInput, outputService, gridService, cancellableCoordinateInput, new GOLRenderService(), cellStateCalculationService);
        return gameOfLife;
    }

    private static MinesweeperRunner configureMinesweeperRunner(ConsoleOutputService outputService, ConsoleInputSource inputSource, DetermineNeighborsService determineNeighborsService) {
        SelectInput selectInput = new SelectInputImpl(outputService, new NumberSelectionInput(outputService, inputSource));
        RevealInteraction revealInteraction = new RevealInteraction(new RevealCellService(new DetermineNeighborsService()));
        Map<Integer, CellInteraction<MSCell, MSGameState>> interactions = new HashMap<>();
        interactions.put(1, new RevealInteraction(new RevealCellService(determineNeighborsService)));
        interactions.put(2, new ToggleFlagInteraction());
        MinesweeperRunner minesweeper = new MinesweeperRunner(
                new MSGridInitService(),
                new MineService(),
                new MineCountCalculator(new DetermineNeighborsService()),
                interactions,
                revealInteraction,
                selectInput,
                new CoordinateInput(outputService, inputSource),
                new MSRenderService(),
                outputService
        );
        return minesweeper;
    }
}