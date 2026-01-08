package dev.saathoff.gameoflife.display;

import dev.saathoff.gameoflife.data.GOLCell;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.grid.display.GridRenderService;


public class GOLRenderService implements GridRenderService<GOLCell> {


    @Override
    public String renderGrid(Grid<GOLCell> grid) {
        StringBuilder builder = new StringBuilder();
        int rows = grid.getRowCount();
        int columns = grid.getColumnCount();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GOLCell cell = grid.getCell(i, j);
                builder.append(cell.isAlive() ? "X" : "O");
            }
            builder.append("\n");

        }
        return builder.toString();
    }
}
