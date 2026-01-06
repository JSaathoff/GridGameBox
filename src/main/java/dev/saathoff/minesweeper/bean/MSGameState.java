package dev.saathoff.minesweeper.bean;

import dev.saathoff.game.data.GameState;

public class MSGameState implements GameState {

    private boolean minesPlaced;

    private Outcome outcome;

    private int flagCount;

    private int revealedCellCount;

    private Difficulty difficulty;

    public MSGameState() {
    }

    public MSGameState(boolean minesPlaced, Outcome outcome, int flagCount, int revealedCellCount, Difficulty difficulty) {
        this.minesPlaced = minesPlaced;
        this.outcome = outcome;
        this.flagCount = flagCount;
        this.revealedCellCount = revealedCellCount;
        this.difficulty = difficulty;
    }

    public boolean isMinesPlaced() {
        return minesPlaced;
    }

    public void setMinesPlaced(boolean minesPlaced) {
        this.minesPlaced = minesPlaced;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getFlagCount() {
        return flagCount;
    }

    public void setFlagCount(int flagCount) {
        this.flagCount = flagCount;
    }

    public int getRevealedCellCount() {
        return revealedCellCount;
    }

    public void setRevealedCellCount(int revealedCellCount) {
        this.revealedCellCount = revealedCellCount;
    }
}
