package dev.saathoff.gameoflife.display;

import dev.saathoff.gameoflife.data.GOLCell;
import dev.saathoff.grid.display.AbstractGridRenderService;


public class GOLRenderService extends AbstractGridRenderService<GOLCell> {

    @Override
    protected String renderCell(GOLCell cell) {
        return cell.isAlive() ? "X" : "O";

    }
}
