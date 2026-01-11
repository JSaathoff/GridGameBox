package dev.saathoff.service;

import dev.saathoff.gameoflife.data.GOLCell;
import dev.saathoff.gameoflife.service.CellStateCalculationService;
import dev.saathoff.gameoflife.service.GOLGridInitService;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.grid.service.DetermineNeighborsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GOLCellStateCalculationServiceTest {
    GOLGridInitService gridService;

    CellStateCalculationService cellStateCalculationService;

    @BeforeEach
    void setup() {
        this.gridService = new GOLGridInitService();
        this.cellStateCalculationService = new CellStateCalculationService(new DetermineNeighborsService(), gridService);
    }

    private Grid<GOLCell> createGrid(int aliveNeighbors, boolean initialCellState) {

        Grid<GOLCell> grid = gridService.generateNewGrid(3, 3);

        GOLCell cell = new GOLCell();
        cell.setAlive(initialCellState);
        grid.setCell(1, 1, cell);

        int[][] neighborCords = {
                {0, 0}, {0, 1}, {0, 2},
                {1, 0}, {1, 2},
                {2, 0}, {2, 1}, {2, 2}
        };

        for (int i = 0; i < aliveNeighbors; i++) {
            int row = neighborCords[i][0];
            int column = neighborCords[i][1];
            GOLCell neighborCell = grid.getCell(row, column);
            neighborCell.setAlive(true);
        }

        return grid;
    }

    @ParameterizedTest(name = "Neighbors={0}, Start={1} -> End={2}")
    @CsvSource({
            // Neighbors, isAlive, expectedNextState
            "0, true,  false", // Underpopulation
            "1, true,  false", // Underpopulation
            "2, true,  true",  // Survival
            "3, true,  true",  // Survival
            "4, true,  false", // Overpopulation
            "5, true,  false", // Overpopulation
            "6, true,  false", // Overpopulation
            "7, true,  false", // Overpopulation
            "8, true,  false", // Overpopulation

            "0, false, false", // Stasis
            "1, false, false", // Stasis
            "2, false, false", // Stasis
            "3, false, true",  // Reproduction
            "4, false, false", // Stasis
            "5, false, false", // Stasis
            "6, false, false", // Stasis
            "7, false, false", // Stasis
            "8, false, false"  // Stasis
    })
    @DisplayName("GOL Rule Evaluation Test")
    public void testCellStateCalculation(int aliveNeighbors,
                                         boolean cellStateBefore,
                                         boolean expectedCellStateAfter) {
        // GIVEN
        Grid<GOLCell> grid = createGrid(aliveNeighbors, cellStateBefore);
        this.cellStateCalculationService.setDetermineNeighborsService(new DetermineNeighborsService());
        // WHEN
        GOLCell nextGenCell = this.cellStateCalculationService.calculateCellState(grid, 1, 1);
        // THEN
        assertEquals(expectedCellStateAfter, nextGenCell.isAlive(),
                String.format("Expected state %s with %d alive neighbors starting from %s",
                        expectedCellStateAfter, aliveNeighbors, cellStateBefore));
    }

}