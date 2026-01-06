package dev.saathoff.minesweeper.bean;

import dev.saathoff.io.output.ConsoleOutputService;
import dev.saathoff.minesweeper.display.MSDisplayService;
import dev.saathoff.minesweeper.service.outcome.LoseOutcomeHandler;
import dev.saathoff.minesweeper.service.outcome.OutcomeHandler;
import dev.saathoff.minesweeper.service.outcome.WonOutcomeHandler;

public enum Outcome {
    WON(new WonOutcomeHandler(new MSDisplayService(), new ConsoleOutputService())),
    LOST(new LoseOutcomeHandler(new MSDisplayService(), new ConsoleOutputService())),
    ;

    private final OutcomeHandler outcomeHandler;

    Outcome(OutcomeHandler outcomeHandler) {
        this.outcomeHandler = outcomeHandler;
    }

    public OutcomeHandler getOutcomeHandler() {
        return outcomeHandler;
    }
}
