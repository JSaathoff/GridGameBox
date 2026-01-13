package dev.saathoff.minesweeper.display;

import dev.saathoff.grid.display.AbstractGridRenderService;
import dev.saathoff.minesweeper.data.MSCell;

public class MSRenderService extends AbstractGridRenderService<MSCell> {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";
    private static final String GRAY = "\u001B[90m";

    @Override
    public String renderCell(MSCell cell) {
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
