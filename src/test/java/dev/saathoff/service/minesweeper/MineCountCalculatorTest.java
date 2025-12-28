package dev.saathoff.service.minesweeper;

import dev.saathoff.bean.Grid;
import dev.saathoff.bean.MSCell;
import dev.saathoff.service.grid.dto.Coordinates;
import dev.saathoff.service.grid.impl.DetermineNeighborsService;
import dev.saathoff.service.grid.impl.MSGridService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MineCountCalculatorTest {

    @Mock
    DetermineNeighborsService mockNeighborsService;

    private MSGridService gridService = new MSGridService();

    @Test
    void determineMineCountForGrid() {
        // GIVEN
        Grid<MSCell> grid = this.gridService.generateNewGrid(3, 3);
        MSCell cell = grid.getCell(0, 0);
        cell.setMine(true);
        cell = grid.getCell(1, 0);
        cell.setMine(true);
        List<Coordinates> neighbors = new ArrayList<>();
        neighbors.add(new Coordinates(0,0));
        neighbors.add(new Coordinates(0,1));
        neighbors.add(new Coordinates(0,2));
        neighbors.add(new Coordinates(1,0));
        neighbors.add(new Coordinates(1,2));
        neighbors.add(new Coordinates(2,0));
        neighbors.add(new Coordinates(2,1));
        neighbors.add(new Coordinates(2,2));

        when(mockNeighborsService.determineNeighborCoordinates(grid, 1,1)).thenReturn(neighbors);

        // WHEN
        MineCountCalculator countCalculator = new MineCountCalculator(mockNeighborsService);
        countCalculator.determineMineCountForCell(grid, 1,1);
        // THEN
        assertEquals(2, grid.getCell(1,1).getMineCount());

    }
}