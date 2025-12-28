package dev.saathoff.service.grid.impl;

import dev.saathoff.bean.ArrayGrid;
import dev.saathoff.bean.Grid;
import dev.saathoff.service.grid.GridService;
import dev.saathoff.service.grid.dto.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DetermineNeighborsServiceTest {
    private DetermineNeighborsService service;

    @BeforeEach
    void setUp() {
        // This runs before every @Test method
        service = new DetermineNeighborsService();
    }
    @ParameterizedTest
    @CsvSource({
            "0, 0, 3", // Top-left corner should have 3 neighbors
            "0, 1, 5", // Top edge should have 5 neighbors
            "1, 1, 8", // Center should have 8 neighbors
            "2, 2, 3"  // Bottom-right corner should have 3 neighbors
    })
    void shouldReturnCorrectNumberOfNeighbors(int row, int col, int expectedCount) {
        // GIVEN
        Grid grid = new ArrayGrid(3,3);

        // WHEN
        List<Coordinates> neighbors = service.determineNeighborCoordinates(grid, row, col);

        // THEN
        assertEquals(expectedCount, neighbors.size(),
                String.format("Cell at (%d, %d) should have %d neighbors", row, col, expectedCount));
    }
}