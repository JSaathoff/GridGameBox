package dev.saathoff.minesweeper.bean;

import dev.saathoff.game.data.Cell;

public class MSCell implements Cell {
    private boolean isMine;
    private boolean isFlagged;
    private boolean isRevealed;
    private long mineCount;

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public long getMineCount() {
        return mineCount;
    }

    public void setMineCount(long mineCount) {
        this.mineCount = mineCount;
    }
}
