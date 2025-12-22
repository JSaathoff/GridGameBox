package dev.saathoff.service;

import dev.saathoff.bean.Cell;
import dev.saathoff.bean.Grid;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CellStateCalculationServiceTest {

    CellStateCalculationService service = new CellStateCalculationService();

    public static Stream<Arguments> testData() {
        return Stream.of(
                // ALIVE cell scenarios:
                Arguments.of(0, true, false), // Rule 1: Underpopulation (< 2)
                Arguments.of(1, true, false), // Rule 1: Underpopulation (< 2)
                Arguments.of(2, true, true),  // Rule 2: Survival (= 2)
                Arguments.of(3, true, true),  // Rule 2: Survival (= 3)
                Arguments.of(4, true, false), // Rule 1: Overpopulation (> 3)
                Arguments.of(5, true, false), // Rule 1: Overpopulation (> 3)
                Arguments.of(6, true, false), // Rule 1: Overpopulation (> 3)
                Arguments.of(7, true, false), // Rule 1: Overpopulation (> 3)
                Arguments.of(8, true, false), // Rule 1: Overpopulation (> 3)

                // DEAD cell scenarios:
                Arguments.of(0, false, false), // Rule 4: Stasis (Neighbors != 3)
                Arguments.of(1, false, false), // Rule 4: Stasis (Neighbors != 3)
                Arguments.of(2, false, false), // Rule 4: Stasis (Neighbors != 3)
                Arguments.of(3, false, true),  // Rule 3: Reproduction (= 3)
                Arguments.of(4, false, false), // Rule 4: Stasis (Neighbors != 3)
                Arguments.of(5, false, false), // Rule 4: Stasis (Neighbors != 3)
                Arguments.of(6, false, false), // Rule 4: Stasis (Neighbors != 3)
                Arguments.of(7, false, false), // Rule 4: Stasis (Neighbors != 3)
                Arguments.of(8, false, false)  // Rule 4: Stasis (Neighbors != 3)
        );
    }

    private Grid createGrid(int aliveNeighbors, boolean initialCellState) {
        GridService gridService = new GridService();
        Grid grid = gridService.generateNewGrid(3, 3);

        grid.getGrid().get(1).get(1).setAlive(initialCellState);

        int[][] neighborCords = {
                {0,0}, {0,1}, {0,2},
                {1,0},        {1,2},
                {2,0}, {2,1}, {2,2}
        };

        for (int i = 0; i < aliveNeighbors; i++) {
            int row = neighborCords[i][0];
            int column = neighborCords[i][1];
            grid.getGrid().get(row).get(column).setAlive(true);
        }

        return grid;
    }

    @ParameterizedTest(name = "Neighbors={0}, Start={1} -> End={2}")
    @MethodSource("testData")
    @DisplayName("GOL Rule Evaluation Test")
    public void testCellStateCalculation(int aliveNeighbors,
                     boolean cellStateBefore,
                     boolean expectedCellStateAfter){
        //GIVEN
        Grid grid = createGrid(aliveNeighbors, cellStateBefore);
        //WHEN
        Cell nextGenCell = this.service.calculateCellState(grid, 1, 1);
        //THEN
        assertEquals(expectedCellStateAfter, nextGenCell.isAlive(),
                String.format("Expected state %s with %d alive neighbors starting from %s",
                        expectedCellStateAfter, aliveNeighbors, cellStateBefore));
    }

}