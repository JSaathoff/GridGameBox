package dev.saathoff.minesweeper.interaction;

import dev.saathoff.game.data.GameState;
import dev.saathoff.game.interaction.CellInteraction;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.minesweeper.bean.MSCell;
import dev.saathoff.minesweeper.bean.MSGameState;

public class ToggleFlagInteraction implements CellInteraction<MSCell, MSGameState> {
    @Override
    public void interact(MSGameState gameState, Grid<MSCell> grid, int row, int column) {
        //TODO: throw error if already revealed
        MSCell cell = grid.getCell(row, column);
        gameState.setFlagCount(
                cell.isFlagged()
                        ? gameState.getFlagCount() -1
                        : gameState.getFlagCount() + 1
        );
        cell.setFlagged(!cell.isFlagged());
    }

    @Override
    public String getLabelForSelection() {
        return "Toggle Flag";
    }
}
