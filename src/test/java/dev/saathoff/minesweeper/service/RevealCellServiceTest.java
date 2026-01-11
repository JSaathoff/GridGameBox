package dev.saathoff.minesweeper.service;

import dev.saathoff.grid.data.Grid;
import dev.saathoff.grid.service.DetermineNeighborsService;
import dev.saathoff.minesweeper.bean.MSCell;
import dev.saathoff.minesweeper.bean.MSGameState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RevealCellServiceTest {

    @Test
    void revealCell() {
        // GIVEN
        MSGridInitService gridService = new MSGridInitService();
        Grid<MSCell> grid = gridService.generateNewGrid(5, 5);
        RevealCellService revealService = new RevealCellService(new DetermineNeighborsService());
        MineCountCalculator mineCountCalculator = new MineCountCalculator(new DetermineNeighborsService());

        /*
           0  1 2 3 4
        0 [.] . M . .
        1  .  . M . .
        2  M  M M . .
        3  .  . . . .
        4  .  . . . .
         */

        grid.getCell(0, 2).setMine(true);
        grid.getCell(1, 2).setMine(true);
        grid.getCell(2, 2).setMine(true);
        grid.getCell(2, 1).setMine(true);
        grid.getCell(2, 0).setMine(true);

        mineCountCalculator.setMineCountsForGrid(grid);

        // WHEN
        revealService.revealCell(new MSGameState(), grid, 0, 0);

        // THEN
        assertTrue(grid.getCell(0, 0).isRevealed(), "Starting cell should be revealed");
        assertTrue(grid.getCell(0, 1).isRevealed(), "Immediate neighbor should be revealed");
        assertTrue(grid.getCell(1, 0).isRevealed(), "Immediate neighbor should be revealed");
        assertTrue(grid.getCell(1, 1).isRevealed(), "Diagonal neighbor should be revealed");

        // THEN
        assertTrue(grid.getCell(0, 1).getMineCount() > 0);

        // THEN
        assertFalse(grid.getCell(0, 3).isRevealed(), "Cells behind the mine wall should NOT be revealed");
        assertFalse(grid.getCell(4, 4).isRevealed(), "Distant cells should NOT be revealed");

    }
}