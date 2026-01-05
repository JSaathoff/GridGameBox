package dev.saathoff.minesweeper.gameloop;

import dev.saathoff.game.RunnableGame;
import dev.saathoff.game.data.exception.IllegalMoveException;
import dev.saathoff.game.interaction.CellInteraction;
import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.grid.display.GridDisplayService;
import dev.saathoff.io.CoordinateInput;
import dev.saathoff.io.select.SelectInput;
import dev.saathoff.minesweeper.bean.Difficulty;
import dev.saathoff.minesweeper.bean.MSCell;
import dev.saathoff.minesweeper.bean.MSGameState;
import dev.saathoff.minesweeper.bean.MSGameStatus;
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

    public MinesweeperRunner(MSGridService gridService, MineService mineService, MineCountCalculator mineCountCalculator, Map<Integer, CellInteraction<MSCell,  MSGameState>> cellInteractions, RevealInteraction revealInteraction, SelectInput selectInput, CoordinateInput coordinateInput, GridDisplayService<MSCell> displayService) {
        this.gridService = gridService;
        this.mineService = mineService;
        this.mineCountCalculator = mineCountCalculator;
        this.cellInteractions = cellInteractions;
        this.revealInteraction = revealInteraction;
        this.selectInput = selectInput;
        this.coordinateInput = coordinateInput;
        this.displayService = displayService;
    }

    public void registerInteractions(CellInteraction<MSCell, MSGameState>... interactions){
        for(int i = 1; i <= interactions.length; i++){
            cellInteractions.put(i, interactions[i - 1]);
        }
    }

    @Override
    public void runGame() {
        // Configure Game
        MSGameState gameState = configureGame();
        Difficulty difficulty = gameState.getDifficulty();
        Grid<MSCell> grid = gridService.generateNewGrid(difficulty.getRowCount(), difficulty.getColumnCount());

        this.runGameLoop(gameState, grid);
        displayService.displayGridState(grid);
        switch (gameState.getGameStatus()){
            case WON -> System.out.println("Yeah you won");
            case LOST -> System.out.println("Nooo you lost");
        }

    }

    private MSGameState configureGame() {
        //Ask for difficulty
        Difficulty difficulty = selectInput.selectFromEnum("Choose Difficulty", Difficulty.class);

        //based on difficulty decide the mine count
        MSGameState gameState = new MSGameState(false, MSGameStatus.RUNNING, 0, 0, difficulty );
        return gameState;
    }

    private void runGameLoop(MSGameState gameState, Grid<MSCell> grid) {
        System.out.println(this.displayService.displayGridState(grid));
        Coordinate firstMove = coordinateInput.getCoordinate("Which cell:", grid);
        initializeBoard(grid, gameState, firstMove);

        while (gameState.getGameStatus() == MSGameStatus.RUNNING) {
            System.out.println(this.displayService.displayGridState(grid));

            CellInteraction<MSCell, MSGameState> interaction = selectInput.select("Which action:", cellInteractions);
            Coordinate clickedPosition = coordinateInput.getCoordinate("Which cell:", grid);
            try {
                interaction.interact(gameState, grid, clickedPosition.row(), clickedPosition.column());
            } catch (IllegalMoveException e) {
                System.out.println(e.getMessage());
            }

            if (checkWinCondition(grid, gameState)) {
                gameState.setGameStatus(MSGameStatus.WON);
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
}
