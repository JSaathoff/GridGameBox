package dev.saathoff.grid.display;

import dev.saathoff.grid.bean.Grid;

public interface GridDisplayService<T> {
    String displayGridState(Grid<T> grid);
}
