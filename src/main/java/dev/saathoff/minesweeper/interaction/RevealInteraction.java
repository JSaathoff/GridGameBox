package dev.saathoff.minesweeper.interaction;

import dev.saathoff.game.data.exception.IllegalMoveException;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.io.input.select.Selectable;
import dev.saathoff.minesweeper.bean.MSCell;
import dev.saathoff.game.interaction.CellInteraction;
import dev.saathoff.minesweeper.bean.MSGameState;
import dev.saathoff.minesweeper.bean.Outcome;
import dev.saathoff.minesweeper.service.RevealCellService;

public class RevealInteraction implements CellInteraction<MSCell, MSGameState>, Selectable {


    private RevealCellService revealCellService;

    public RevealInteraction( RevealCellService revealCellService) {
        this.revealCellService = revealCellService;
    }

    @Override
    public void interact(MSGameState gameState, Grid<MSCell> grid, int row, int column) throws IllegalMoveException {
        MSCell cell = grid.getCell(row, column);
        if(cell.isMine()){
            gameState.setOutcome(Outcome.LOST);
        }
        if (cell.isFlagged()) {
            throw new IllegalMoveException("Cannot reveal a flagged cell!");
        }
        if (cell.isRevealed()) {
            throw new IllegalMoveException("This cell is already open.");
        }
        this.revealCellService.revealCell(gameState, grid, row, column);
    }

    @Override
    public String getLabelForSelection() {
        return "Reveal Cell";
    }
}
