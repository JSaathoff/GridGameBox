package dev.saathoff.service.minesweeper;

import dev.saathoff.bean.Grid;
import dev.saathoff.bean.MSCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

public class MineService {

    public Grid<MSCell> placeMines(Grid<MSCell> grid, int bombCount, int clickedRow, int clickedColumn, RandomGenerator random){
        int rows = grid.getRowCount();
        int cols = grid.getColumnCount();

        List<Integer> indices = this.getFlatIndicesOfPossibleMineSpots(rows, cols, clickedRow, clickedColumn);

        Collections.shuffle(indices, (Random) random);

        for (int i = 0; i < bombCount; i++) {
            int flatIndex = indices.get(i);

            int row = flatIndex / cols;
            int col = flatIndex % cols;

            MSCell cell = grid.getCell(row, col);
            cell.setMine(true);
        }

        return grid;
    }

    private List<Integer> getFlatIndicesOfPossibleMineSpots(int rows, int cols, int clickedRow, int clickedColumn) {
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < rows * cols; i++) {
            int r = i / cols;
            int c = i % cols;
            // Skip clicked cell and direct neighbors
            if (Math.abs(r - clickedRow) <= 1 && Math.abs(c - clickedColumn) <= 1) {
                continue;
            }
            indices.add(i);
        }
        return indices;
    }


}
