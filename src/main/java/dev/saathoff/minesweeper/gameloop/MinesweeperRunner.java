package dev.saathoff.minesweeper.gameloop;

import dev.saathoff.game.RunnableGame;
import dev.saathoff.game.data.GameState;
import dev.saathoff.game.interaction.CellInteraction;
import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.minesweeper.bean.Difficulty;
import dev.saathoff.minesweeper.bean.MSCell;
import dev.saathoff.minesweeper.bean.MSGameState;
import dev.saathoff.minesweeper.bean.MSGameStatus;
import dev.saathoff.minesweeper.service.MSGridService;
import dev.saathoff.minesweeper.service.MineCountCalculator;
import dev.saathoff.minesweeper.service.MineService;

import java.util.Map;

public class MinesweeperRunner implements RunnableGame {

    private MSGridService gridService;

    private MineService mineService;

    private MineCountCalculator mineCountCalculator;

    private CellInteraction<MSCell> revealInteraction;

    private Map<Integer, CellInteraction<MSCell>> cellInteractions;

    public MinesweeperRunner(MSGridService gridService, MineService mineService, MineCountCalculator mineCountCalculator, CellInteraction<MSCell> revealInteraction) {
        this.gridService = gridService;
        this.mineService = mineService;
        this.mineCountCalculator = mineCountCalculator;
        this.revealInteraction = revealInteraction;
    }

    private void registerInteractions(CellInteraction<MSCell>... interactions){
        for(int i = 1; i <= interactions.length; i++){
            cellInteractions.put(i, interactions[i]);
        }
    }

    @Override
    public void runGame() {
        // Configure Game
        MSGameState gameState = configureGame();
        Grid<MSCell> grid = switch (gameState.getDifficulty()){
            case EASY -> gridService.generateNewGrid(10,10);
            case MEDIUM -> gridService.generateNewGrid(20,20);
            case HARD -> gridService.generateNewGrid(30,30);
        };
        this.runGameLoop(gameState, grid);
        switch (gameState.getGameStatus()){

        }

    }

    private MSGameState configureGame() {
        //Ask for difficulty
        //based on difficulty decide the mine count
        MSGameState gameState = new MSGameState(false, MSGameStatus.RUNNING, 5, Difficulty.EASY );
        return gameState;
    }

    private void runGameLoop(MSGameState gameState, Grid<MSCell> grid) {
        Coordinate firstMove = new Coordinate(5, 5); // TODO: Real input
        initializeBoard(grid, gameState, firstMove);

        while (gameState.getGameStatus() == MSGameStatus.RUNNING) {
            // TODO: Real input logic
            Coordinate clickedPosition = new Coordinate(2, 2);

            processTurn(gameState, grid, clickedPosition);

            if (checkWinCondition(grid)) {
                gameState.setGameStatus(MSGameStatus.WON);
            }
        }
    }

    private void initializeBoard(Grid<MSCell> grid, MSGameState state, Coordinate firstMove) {
        mineService.placeMines(grid, state.getMineCount(), firstMove.row(), firstMove.column());
        mineCountCalculator.setMineCountsForGrid(grid);
        revealInteraction.interact(grid, firstMove.row(), firstMove.column());
        // displayGrid(grid);
    }

    private void processTurn(MSGameState gameState, Grid<MSCell> grid, Coordinate position) {
        MSCell clickedCell = grid.getCell(position);

        if (isInvalidMove(clickedCell)) {
            return;
        }

        revealInteraction.interact(grid, position.row(), position.column());

        if (clickedCell.isMine()) {
            gameState.setGameStatus(MSGameStatus.LOST);
        }
    }

    private boolean isInvalidMove(MSCell cell) {
        if (cell.isRevealed()) {
            // Inform user: already revealed
            return true;
        }
        if (cell.isFlagged()) {
            // Inform user: cell is flagged
            return true;
        }
        return false;
    }

    private boolean checkWinCondition(Grid<MSCell> grid) {
        return grid.asStream()
                .filter(cell -> !cell.isMine()) // Look at safe cells
                .allMatch(MSCell::isRevealed);   // Are they ALL revealed?
    }

    public CellInteraction<MSCell> getRevealInteraction() {
        return revealInteraction;
    }

    public void setRevealInteraction(CellInteraction<MSCell> revealInteraction) {
        this.revealInteraction = revealInteraction;
    }

    public MineCountCalculator getMineCountCalculator() {
        return mineCountCalculator;
    }

    public void setMineCountCalculator(MineCountCalculator mineCountCalculator) {
        this.mineCountCalculator = mineCountCalculator;
    }
}
