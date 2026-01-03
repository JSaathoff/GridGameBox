package dev.saathoff.minesweeper.bean;

import dev.saathoff.game.data.GameMetadata;

public class MSMetaData implements GameMetadata {

    public MSMetaData(boolean minesPlaced, MSGameStatus gameStatus, int mineCount) {
        this.minesPlaced = minesPlaced;
        this.gameStatus = gameStatus;
        this.mineCount = mineCount;
    }

    private boolean minesPlaced;

    private MSGameStatus gameStatus;

    private int mineCount;

    public boolean isMinesPlaced() {
        return minesPlaced;
    }

    public void setMinesPlaced(boolean minesPlaced) {
        this.minesPlaced = minesPlaced;
    }

    public MSGameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(MSGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getMineCount() {
        return mineCount;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }
}
