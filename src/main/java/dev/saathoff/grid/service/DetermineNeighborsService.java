package dev.saathoff.grid.service;

import dev.saathoff.grid.bean.Grid;
import dev.saathoff.grid.bean.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class DetermineNeighborsService {

    private static final int[][] NEIGHBOR_OFFSETS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},          { 0, 1},
            { 1, -1}, { 1, 0}, { 1, 1}
    };

    public List<Coordinates> determineNeighborCoordinates(Grid grid, int row, int column) {
        List<Coordinates> neighborIndices = new ArrayList<>();
        for (int[] offset : NEIGHBOR_OFFSETS) {
            int neighborRow = row + offset[0];
            int neighborCol = column + offset[1];

            boolean isWithinBounds =
                    neighborRow >= 0 && neighborRow < grid.getRowCount() &&
                            neighborCol >= 0 && neighborCol < grid.getColumnCount();

            if (isWithinBounds) {
                Coordinates neighborCords = new Coordinates(neighborRow, neighborCol);
                neighborIndices.add(neighborCords);
            }
        }

        return neighborIndices;
    }

}
