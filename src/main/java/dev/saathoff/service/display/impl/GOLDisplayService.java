package dev.saathoff.service.display.impl;

import dev.saathoff.bean.GOLCell;
import dev.saathoff.bean.Grid;
import dev.saathoff.service.display.GridDisplayService;


public class GOLDisplayService implements GridDisplayService<GOLCell> {


    @Override
    public String displayGridState(Grid<GOLCell> grid) {
        StringBuilder builder = new StringBuilder();
        int rows = grid.getRowCount();
        int columns = grid.getColumnCount();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                GOLCell cell = grid.getCell(i, j);
                builder.append(cell.isAlive() ? "X" : "O");
            }
            builder.append("\n");

        }
        return builder.toString();
    }
}
