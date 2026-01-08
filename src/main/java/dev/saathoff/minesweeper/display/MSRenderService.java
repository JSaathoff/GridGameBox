package dev.saathoff.minesweeper.display;

import dev.saathoff.grid.data.Grid;
import dev.saathoff.grid.display.GridRenderService;
import dev.saathoff.minesweeper.bean.MSCell;

public class MSRenderService implements GridRenderService<MSCell> {
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";
    private static final String GRAY = "\u001B[90m";

    @Override
    public String renderGrid(Grid<MSCell> grid) {
        StringBuilder sb = new StringBuilder("\n");

        this.generateColumnHeader(grid, sb);

        for (int r = 0; r < grid.getRowCount(); r++) {
            generateRow(grid, sb, r);
        }

        return sb.toString();
    }

    private void generateRow(Grid<MSCell> grid, StringBuilder sb, int r) {
        sb.append(String.format("%2d |", r)); // Row Header

        for (int c = 0; c < grid.getColumnCount(); c++) {
            MSCell cell = grid.getCell(r, c);
            sb.append(" ").append(formatCell(cell)).append(" ");
        }
        sb.append("\n");
    }

    private void generateColumnHeader(Grid<MSCell> grid, StringBuilder sb) {
        // 1. Create Column Headers (Numbers at the top)
        sb.append("    "); // Offset for row numbers
        for (int c = 0; c < grid.getColumnCount(); c++) {
            sb.append(String.format("%2d ", c));
        }
        sb.append("\n    ").append("—".repeat(grid.getColumnCount() * 3)).append("\n");
    }

    private String formatCell(MSCell cell) {
        if (cell.isFlagged()) {
            return RED + "F" + RESET;
        }

        if (!cell.isRevealed()) {
            return GRAY + "0" + RESET;
        }

        if (cell.isMine()) {
            return "B";
        }

        long count = cell.getMineCount();
        if (count == 0) return " ";

        return getColorForNumber(count) + count + RESET;
    }

    private String getColorForNumber(long count) {
        return switch ((int) count) {
            case 1 -> BLUE;
            case 2 -> GREEN;
            case 3 -> RED;
            case 4 -> YELLOW;
            case 5 -> CYAN;
            default -> RED;
        };
    }
}
