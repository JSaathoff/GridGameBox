package dev.saathoff.gameoflife.service;

import dev.saathoff.gameoflife.data.GOLCell;
import dev.saathoff.grid.service.AbstractGridInitService;


public class GOLGridInitService extends AbstractGridInitService<GOLCell> {

    @Override
    protected GOLCell initializeCell() {
        GOLCell cell = new GOLCell();
        cell.setAlive(false);
        return cell;
    }

}
