package dev.saathoff.grid.service;

import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.grid.data.Grid;

import java.util.ArrayList;
import java.util.List;

public class DetermineNeighborsService {

    private static final int[][] NEIGHBOR_OFFSETS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

    public List<Coordinate> determineNeighborCoordinates(Grid grid, int row, int column) {
        List<Coordinate> neighbors = new ArrayList<>();
        for (int[] offset : NEIGHBOR_OFFSETS) {
            int neighborRow = row + offset[0];
            int neighborCol = column + offset[1];

            boolean isWithinBounds =
                    neighborRow >= 0 && neighborRow < grid.getRowCount() &&
                            neighborCol >= 0 && neighborCol < grid.getColumnCount();

            if (isWithinBounds) {
                Coordinate neighborCords = new Coordinate(neighborRow, neighborCol);
                neighbors.add(neighborCords);
            }
        }

        return neighbors;
    }

}
