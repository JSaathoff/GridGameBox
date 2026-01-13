package dev.saathoff.minesweeper.service;

import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.minesweeper.data.MSCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

public class MineService {

    public Grid<MSCell> placeMines(Grid<MSCell> grid, int mineCount, int clickedRow, int clickedColumn) {
        return this.placeMines(grid, mineCount, clickedRow, clickedColumn, new Random());
    }

    public Grid<MSCell> placeMines(Grid<MSCell> grid, int mineCount, int clickedRow, int clickedColumn, RandomGenerator random) {
        int rows = grid.getRowCount();
        int cols = grid.getColumnCount();

        List<Coordinate> availableMineSpots = this.getAvailableMineSpots(rows, cols, clickedRow, clickedColumn);

        Collections.shuffle(availableMineSpots, (Random) random);

        for (int i = 0; i < mineCount; i++) {
            Coordinate spot = availableMineSpots.get(i);

            int row = spot.row();
            int col = spot.column();

            MSCell cell = grid.getCell(row, col);
            cell.setMine(true);
        }

        return grid;
    }

    private List<Coordinate> getAvailableMineSpots(int rows, int cols, int clickedRow, int clickedColumn) {
        List<Coordinate> availableMineSpots = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                boolean isSafeZone = Math.abs(row - clickedRow) <= 1 && Math.abs(col - clickedColumn) <= 1;
                if (!isSafeZone) {
                    availableMineSpots.add(new Coordinate(row, col));
                }
            }
        }
        return availableMineSpots;
    }
}
