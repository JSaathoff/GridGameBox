package dev.saathoff.minesweeper.interaction;

import dev.saathoff.game.interaction.CellInteraction;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.minesweeper.bean.MSCell;
import dev.saathoff.minesweeper.bean.MSGameState;

public class RevealAllNeighborsOfRevealedCellInteraction implements CellInteraction<MSCell, MSGameState> {
    @Override
    public void interact(MSGameState gameState, Grid<MSCell> grid, int row, int column) {
        //TODO: Implement click on number. If enough neighbors are flagged to match the number, reveal all neighbors except the flagged once. Else ghost reveal them.
    }

    @Override
    public String getLabelForSelection() {
        return "Reveal neighbors of number cell";
    }
}
