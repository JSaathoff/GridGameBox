package dev.saathoff.service.minesweeper;

import dev.saathoff.grid.data.Grid;
import dev.saathoff.minesweeper.data.MSCell;
import dev.saathoff.minesweeper.service.MSGridInitService;
import dev.saathoff.minesweeper.service.MineService;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MineServiceTest {
    @Test
    void placeMines_ShouldPlaceMinesInPredictableLocations_WithFixedSeed() {
        // GIVEN
        MSGridInitService gridService = new MSGridInitService();
        MineService mineService = new MineService();
        Grid<MSCell> grid = gridService.generateNewGrid(5, 5);
        Random fixedRandom = new Random(1337);
        // WHEN
        mineService.placeMines(grid, 3, 2, 2, fixedRandom);
        // THEN
        assertTrue(grid.getCell(2, 0).isMine());
        assertTrue(grid.getCell(1, 4).isMine());
        assertTrue(grid.getCell(1, 0).isMine());
    }

    @Test
    void placeMines_ShouldLeaveOutNeighborsOfClickedCell() {
        // GIVEN
        MSGridInitService gridService = new MSGridInitService();
        MineService mineService = new MineService();
        Grid<MSCell> grid = gridService.generateNewGrid(3, 3);
        // WHEN
        mineService.placeMines(grid, 3, 2, 1, new Random());
        // THEN
        assertTrue(grid.getCell(0, 0).isMine());
        assertTrue(grid.getCell(0, 1).isMine());
        assertTrue(grid.getCell(0, 2).isMine());
    }
}