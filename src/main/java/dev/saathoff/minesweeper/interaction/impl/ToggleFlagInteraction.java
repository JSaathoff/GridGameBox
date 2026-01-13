package dev.saathoff.minesweeper.interaction.impl;

import dev.saathoff.grid.data.Grid;
import dev.saathoff.minesweeper.data.MSCell;
import dev.saathoff.minesweeper.data.MSGameState;
import dev.saathoff.minesweeper.data.exception.IllegalMoveException;
import dev.saathoff.minesweeper.interaction.CellInteraction;

public class ToggleFlagInteraction implements CellInteraction<MSCell, MSGameState> {
    @Override
    public void interact(MSGameState gameState, Grid<MSCell> grid, int row, int column) throws IllegalMoveException {
        MSCell cell = grid.getCell(row, column);
        if (cell.isRevealed()) {
            throw new IllegalMoveException("Cell has already been revealed");
        }
        gameState.setFlagCount(
                cell.isFlagged()
                        ? gameState.getFlagCount() - 1
                        : gameState.getFlagCount() + 1
        );
        cell.setFlagged(!cell.isFlagged());
    }

    @Override
    public String getLabelForSelection() {
        return "Toggle Flag";
    }
}
