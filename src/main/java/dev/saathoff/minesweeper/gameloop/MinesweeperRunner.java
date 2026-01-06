package dev.saathoff.minesweeper.gameloop;

import dev.saathoff.game.RunnableGame;
import dev.saathoff.game.data.exception.IllegalMoveException;
import dev.saathoff.game.interaction.CellInteraction;
import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.grid.display.GridDisplayService;
import dev.saathoff.io.input.CoordinateInput;
import dev.saathoff.io.input.select.SelectInput;
import dev.saathoff.io.output.OutputService;
import dev.saathoff.minesweeper.bean.Difficulty;
import dev.saathoff.minesweeper.bean.MSCell;
import dev.saathoff.minesweeper.bean.MSGameState;
import dev.saathoff.minesweeper.bean.Outcome;
import dev.saathoff.minesweeper.interaction.RevealInteraction;
import dev.saathoff.minesweeper.service.MSGridService;
import dev.saathoff.minesweeper.service.MineCountCalculator;
import dev.saathoff.minesweeper.service.MineService;

import java.util.Map;

public class MinesweeperRunner implements RunnableGame {

    private MSGridService gridService;

    private MineService mineService;

    private MineCountCalculator mineCountCalculator;

    private Map<Integer, CellInteraction<MSCell, MSGameState>> cellInteractions;

    private SelectInput selectInput;

    private CoordinateInput coordinateInput;

    private CellInteraction<MSCell, MSGameState> revealInteraction;

    private GridDisplayService<MSCell> displayService;

    private OutputService outputService;

    public MinesweeperRunner(MSGridService gridService, MineService mineService, MineCountCalculator mineCountCalculator, Map<Integer, CellInteraction<MSCell,  MSGameState>> cellInteractions, RevealInteraction revealInteraction, SelectInput selectInput, CoordinateInput coordinateInput, GridDisplayService<MSCell> displayService, OutputService outputService) {
        this.gridService = gridService;
        this.mineService = mineService;
        this.mineCountCalculator = mineCountCalculator;
        this.cellInteractions = cellInteractions;
        this.revealInteraction = revealInteraction;
        this.selectInput = selectInput;
        this.coordinateInput = coordinateInput;
        this.displayService = displayService;
        this.outputService = outputService;
    }

    public void registerInteractions(CellInteraction<MSCell, MSGameState>... interactions){
        for(int i = 1; i <= interactions.length; i++){
            cellInteractions.put(i, interactions[i - 1]);
        }
    }

    @Override
    public void runGame() {
        MSGameState gameState = configureGame();
        Difficulty difficulty = gameState.getDifficulty();
        Grid<MSCell> grid = gridService.generateNewGrid(difficulty.getRowCount(), difficulty.getColumnCount());

        this.runGameLoop(gameState, grid);

        gameState.getOutcome().getOutcomeHandler().handleOutcome(gameState, grid);

    }

    private MSGameState configureGame() {

        Difficulty difficulty = selectInput.selectFromEnum("Choose Difficulty", Difficulty.class);

        MSGameState gameState = new MSGameState(false, null, 0, 0, difficulty );
        return gameState;
    }

    private void runGameLoop(MSGameState gameState, Grid<MSCell> grid) {
        outputService.output(this.displayService.displayGridState(grid));
        Coordinate firstMove = coordinateInput.getCoordinate("Which cell:", grid);
        initializeBoard(grid, gameState, firstMove);

        while (gameState.getOutcome() == null) {
            outputService.output(this.displayService.displayGridState(grid));

            CellInteraction<MSCell, MSGameState> interaction = selectInput.select("Which action:", cellInteractions);
            Coordinate clickedPosition = coordinateInput.getCoordinate("Which cell:", grid);
            try {
                interaction.interact(gameState, grid, clickedPosition.row(), clickedPosition.column());
            } catch (IllegalMoveException e) {
                outputService.output(e.getMessage());
            }

            if (checkWinCondition(grid, gameState)) {
                gameState.setOutcome(Outcome.WON);
            }
        }
    }

    private void initializeBoard(Grid<MSCell> grid, MSGameState state, Coordinate firstMove) {
        mineService.placeMines(grid, state.getDifficulty().getMineCount(), firstMove.row(), firstMove.column());
        mineCountCalculator.setMineCountsForGrid(grid);
        revealInteraction.interact(state, grid, firstMove.row(), firstMove.column());
    }

    private boolean checkWinCondition(Grid<MSCell> grid, MSGameState gameState) {
        int totalCells = grid.getRowCount() * grid.getColumnCount();
        int mines = gameState.getDifficulty().getMineCount();
        return gameState.getRevealedCellCount() == totalCells - mines;
    }

    public CellInteraction<MSCell, MSGameState> getRevealInteraction() {
        return revealInteraction;
    }

    public void setRevealInteraction(CellInteraction<MSCell, MSGameState> revealInteraction) {
        this.revealInteraction = revealInteraction;
    }

    public MineCountCalculator getMineCountCalculator() {
        return mineCountCalculator;
    }

    public void setMineCountCalculator(MineCountCalculator mineCountCalculator) {
        this.mineCountCalculator = mineCountCalculator;
    }

    public CoordinateInput getCoordinateInput() {
        return coordinateInput;
    }

    public void setCoordinateInput(CoordinateInput coordinateInput) {
        this.coordinateInput = coordinateInput;
    }

    public OutputService getOutputService() {
        return outputService;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }
}
