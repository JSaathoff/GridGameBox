package dev.saathoff.gameoflife.gameloop;

import dev.saathoff.game.RunnableGame;
import dev.saathoff.gameoflife.data.GOLCell;
import dev.saathoff.gameoflife.display.GOLRenderService;
import dev.saathoff.gameoflife.service.CellStateCalculationService;
import dev.saathoff.gameoflife.service.GOLAbstractGridService;
import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.io.input.impl.CancellableCoordinateInput;
import dev.saathoff.io.input.impl.IntegerInput;
import dev.saathoff.io.input.validator.criteria.CoordinateValidationCriteria;
import dev.saathoff.io.input.validator.criteria.RangeValidationCriteria;
import dev.saathoff.io.output.OutputService;

import java.util.Optional;

public class GameOfLifeRunner implements RunnableGame {

    private IntegerInput integerInput;
    private OutputService outputService;
    private GOLAbstractGridService gridService;
    private CancellableCoordinateInput cancellableCoordinateInput;
    private GOLRenderService renderService;
    private CellStateCalculationService cellStateCalculationService; //new CellStateCalculationService(new DetermineNeighborsService())

    public GameOfLifeRunner(IntegerInput integerInput, OutputService outputService, GOLAbstractGridService gridService, CancellableCoordinateInput cancellableCoordinateInput, GOLRenderService renderService, CellStateCalculationService cellStateCalculationService) {
        this.integerInput = integerInput;
        this.outputService = outputService;
        this.gridService = gridService;
        this.cancellableCoordinateInput = cancellableCoordinateInput;
        this.renderService = renderService;
        this.cellStateCalculationService = cellStateCalculationService;
    }

    @Override
    public void runGame() {
        Grid<GOLCell> grid = this.configureGame();
        this.configureFirstGeneration(grid);
        int generationsToSimulate = this.integerInput.getInput("Enter number of Generations you want to simulate:", new RangeValidationCriteria(1, 100));
        for (int i = 0; i < generationsToSimulate; i++) {
            grid = gridService.calculateNextGridState(grid, this.cellStateCalculationService);
            String renderedGrid = renderService.renderGrid(grid);
            outputService.output(renderedGrid);
        }
    }


    private void configureFirstGeneration(Grid<GOLCell> grid) {
        this.outputService.output("Lets configure the starting generation. You can toggle as many cells as you like. Once you're done just enter q to exit the toggling input.");
        CoordinateValidationCriteria gridBounds = new CoordinateValidationCriteria(0, grid.getRowCount(), 0, grid.getColumnCount());
        while (true) {
            Optional<Coordinate> optionalCoordinate = cancellableCoordinateInput.getInput("Enter row and column separted by comma or space", gridBounds);
            if (optionalCoordinate.isEmpty()) {
                return;
            }
            GOLCell cell = grid.getCell(optionalCoordinate.get());
            cell.setAlive(!cell.isAlive());
            String renderedGrid = renderService.renderGrid(grid);
            outputService.output(renderedGrid);
        }

    }

    private Grid<GOLCell> configureGame() {
        int rows = this.integerInput.getInput("How many rows? ", new RangeValidationCriteria(3, 100));
        int columns = this.integerInput.getInput("How many columns? ", new RangeValidationCriteria(3, 100));
        Grid<GOLCell> grid = gridService.generateNewGrid(rows, columns);
        return grid;
    }

}
