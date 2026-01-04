package dev.saathoff.minesweeper.bean;

import dev.saathoff.game.data.GameState;

public class MSGameState implements GameState {

    private boolean minesPlaced;

    private MSGameStatus gameStatus;

    private int mineCount;

    private Difficulty difficulty;

    public MSGameState(boolean minesPlaced, MSGameStatus gameStatus, int mineCount, Difficulty difficulty) {
        this.minesPlaced = minesPlaced;
        this.gameStatus = gameStatus;
        this.mineCount = mineCount;
        this.difficulty = difficulty;
    }

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

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
