package dev.saathoff.gameoflife.data;

import dev.saathoff.game.data.Cell;

public class GOLCell implements Cell {

    private boolean isAlive;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
