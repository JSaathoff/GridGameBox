package dev.saathoff.service.minesweeper;

import dev.saathoff.bean.Grid;
import dev.saathoff.bean.MSCell;
import dev.saathoff.service.grid.impl.MSGridService;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MineServiceTest {
    @Test
    void placeMines_ShouldPlaceMinesInPredictableLocations_WithFixedSeed() {
        // GIVEN
        MSGridService gridService = new MSGridService();
        MineService mineService = new MineService();
        Grid<MSCell> grid = gridService.generateNewGrid(5,5);
        Random fixedRandom = new Random(1337);
        // WHEN
        mineService.placeMines(grid, 3, 2,2, fixedRandom);
        // THEN
        assertTrue(grid.getCell(2, 0).isMine());
        assertTrue(grid.getCell(1, 4).isMine());
        assertTrue(grid.getCell(1, 0).isMine());
    }

    @Test
    void placeMines_ShouldLeaveOutNeighborsOfClickedCell(){
        // GIVEN
        MSGridService gridService = new MSGridService();
        MineService mineService = new MineService();
        Grid<MSCell> grid = gridService.generateNewGrid(3,3);
        // WHEN
        mineService.placeMines(grid, 3, 2,1, new Random());
        // THEN
        assertTrue(grid.getCell(0, 0).isMine());
        assertTrue(grid.getCell(0, 1).isMine());
        assertTrue(grid.getCell(0, 2).isMine());
    }
}