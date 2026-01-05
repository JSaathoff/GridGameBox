package dev.saathoff.io;

import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.grid.data.Grid;

public interface CoordinateInput {

    Coordinate getCoordinate(String Label, Grid<?> grid);

}
