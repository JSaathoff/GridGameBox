package dev.saathoff.service.interaction;

import dev.saathoff.bean.Grid;

public interface CellInteraction {
     void interact(Grid grid, int row, int column);
}
