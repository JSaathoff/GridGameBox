package dev.saathoff.service.display.impl;

import dev.saathoff.bean.Cell;
import dev.saathoff.bean.Grid;
import dev.saathoff.service.display.GridDisplayService;

import java.util.List;

public class GOLDisplayService implements GridDisplayService {


    @Override
    public String displayGridState(Grid grid) {
        StringBuilder builder = new StringBuilder();
        List<List<Cell>> gridList = grid.getGrid();
        for (List<Cell> row : gridList){
            for(Cell cell: row){
                builder.append(cell.isAlive() ? "X" : "O");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
