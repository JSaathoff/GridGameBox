package dev.saathoff.grid.display;

import dev.saathoff.game.data.Cell;
import dev.saathoff.grid.data.Grid;

public abstract class AbstractGridRenderService<T extends Cell> implements GridRenderService<T> {

    @Override
    public String renderGrid(Grid<T> grid) {
        StringBuilder sb = new StringBuilder("\n");

        this.generateColumnHeader(grid, sb);

        for (int r = 0; r < grid.getRowCount(); r++) {
            generateRow(grid, sb, r);
        }

        return sb.toString();
    }

    private void generateRow(Grid<T> grid, StringBuilder sb, int r) {
        sb.append(String.format("%2d |", r)); // Row Header

        for (int c = 0; c < grid.getColumnCount(); c++) {
            T cell = grid.getCell(r, c);
            sb.append(" ").append(this.renderCell(cell)).append(" ");
        }
        sb.append("\n");
    }

    private void generateColumnHeader(Grid<T> grid, StringBuilder sb) {
        // 1. Create Column Headers (Numbers at the top)
        sb.append("    "); // Offset for row numbers
        for (int c = 0; c < grid.getColumnCount(); c++) {
            sb.append(String.format("%2d ", c));
        }
        sb.append("\n    ").append("—".repeat(grid.getColumnCount() * 3)).append("\n");
    }

    protected abstract String renderCell(T cell);
}
