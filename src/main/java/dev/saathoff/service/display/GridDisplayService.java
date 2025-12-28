package dev.saathoff.service.display;

import dev.saathoff.bean.Grid;

public interface GridDisplayService<T> {
    String displayGridState(Grid<T> grid);
}
