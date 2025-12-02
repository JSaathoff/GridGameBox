package dev.saathoff.service;

import dev.saathoff.bean.Cell;
import dev.saathoff.bean.GameOfLifeCellState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CellStateCalculationServiceTest {

    CellStateCalculationService service = new CellStateCalculationService();

    public static Stream<Arguments> testData() {
        return Stream.of(
                // ALIVE cell scenarios:
                Arguments.of(0, GameOfLifeCellState.ALIVE, GameOfLifeCellState.DEAD), // Rule 1: Underpopulation (< 2)
                Arguments.of(1, GameOfLifeCellState.ALIVE, GameOfLifeCellState.DEAD), // Rule 1: Underpopulation (< 2)
                Arguments.of(2, GameOfLifeCellState.ALIVE, GameOfLifeCellState.ALIVE), // Rule 2: Survival (= 2)
                Arguments.of(3, GameOfLifeCellState.ALIVE, GameOfLifeCellState.ALIVE), // Rule 2: Survival (= 3)
                Arguments.of(4, GameOfLifeCellState.ALIVE, GameOfLifeCellState.DEAD), // Rule 1: Overpopulation (> 3)
                Arguments.of(5, GameOfLifeCellState.ALIVE, GameOfLifeCellState.DEAD), // Rule 1: Overpopulation (> 3)
                Arguments.of(6, GameOfLifeCellState.ALIVE, GameOfLifeCellState.DEAD), // Rule 1: Overpopulation (> 3)
                Arguments.of(7, GameOfLifeCellState.ALIVE, GameOfLifeCellState.DEAD), // Rule 1: Overpopulation (> 3)
                Arguments.of(8, GameOfLifeCellState.ALIVE, GameOfLifeCellState.DEAD), // Rule 1: Overpopulation (> 3)

                // DEAD cell scenarios:
                Arguments.of(0, GameOfLifeCellState.DEAD, GameOfLifeCellState.DEAD), // Rule 4: Stasis (Neighbors != 3)
                Arguments.of(1, GameOfLifeCellState.DEAD, GameOfLifeCellState.DEAD), // Rule 4: Stasis (Neighbors != 3)
                Arguments.of(2, GameOfLifeCellState.DEAD, GameOfLifeCellState.DEAD), // Rule 4: Stasis (Neighbors != 3)
                Arguments.of(3, GameOfLifeCellState.DEAD, GameOfLifeCellState.ALIVE), // Rule 3: Reproduction (= 3)
                Arguments.of(4, GameOfLifeCellState.DEAD, GameOfLifeCellState.DEAD), // Rule 4: Stasis (Neighbors != 3)
                Arguments.of(5, GameOfLifeCellState.DEAD, GameOfLifeCellState.DEAD), // Rule 4: Stasis (Neighbors != 3)
                Arguments.of(6, GameOfLifeCellState.DEAD, GameOfLifeCellState.DEAD), // Rule 4: Stasis (Neighbors != 3)
                Arguments.of(7, GameOfLifeCellState.DEAD, GameOfLifeCellState.DEAD), // Rule 4: Stasis (Neighbors != 3)
                Arguments.of(8, GameOfLifeCellState.DEAD, GameOfLifeCellState.DEAD)  // Rule 4: Stasis (Neighbors != 3)
        );
    }

    private Cell[][] createNeighbors(int aliveCount) {
        Cell[][] neighbors = new Cell[3][3];
        int fullRowsNeeded = aliveCount / 3;
        int lastRowNeeded = aliveCount % 3;
        for(int i = 0; i < fullRowsNeeded; i++){
            for(int j = 0; j < 3; j++){
                Cell neighbor = new Cell();
                neighbor.setCellState(GameOfLifeCellState.ALIVE);
                neighbors[i][j] = neighbor;
            }
        }
        for(int i = 0; i < lastRowNeeded; i++){
            Cell neighbor = new Cell();
            neighbor.setCellState(GameOfLifeCellState.ALIVE);
            neighbors[2][i] = neighbor;
        }
        return neighbors;
    }

    @ParameterizedTest(name = "Neighbors={0}, Start={1} -> End={2}")
    @MethodSource("testData")
    @DisplayName("GOL Rule Evaluation Test")
    public void testCellStateCalculation(int aliveNeighbors,
                     GameOfLifeCellState cellStateBefore,
                     GameOfLifeCellState expectedCellStateAfter){
        //GIVEN
        Cell cell = new Cell();
        cell.setNeighbors(createNeighbors(aliveNeighbors));
        cell.setCellState(cellStateBefore);
        //WHEN
        Cell nextGenCell = this.service.calculateCellState(cell);
        //THEN
        assertEquals(expectedCellStateAfter, nextGenCell.getCellState(),
                String.format("Expected state %s with %d alive neighbors starting from %s",
                        expectedCellStateAfter, aliveNeighbors, cellStateBefore));
    }

}