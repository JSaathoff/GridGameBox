package dev.saathoff.game;

import dev.saathoff.io.input.InputSource;
import dev.saathoff.io.input.select.CancellableSelectInput;
import dev.saathoff.io.output.OutputService;

import java.util.Map;
import java.util.Optional;

public class GameboxRunner {

    private OutputService outputService;

    private InputSource inputSource;

    private Map<Integer, RunnableGame> games;

    private CancellableSelectInput selectInput;

    public GameboxRunner(OutputService outputService, InputSource inputSource, Map<Integer, RunnableGame> games, CancellableSelectInput selectInput) {
        this.outputService = outputService;
        this.inputSource = inputSource;
        this.games = games;
        this.selectInput = selectInput;
    }

    public void runGamebox() {
        this.outputService.output("Welcome to the Grid Game Box!");
        while (true) {
            Optional<RunnableGame> selectedGame = selectInput.select("Which game would you like to play?", games);
            if (selectedGame.isEmpty()) {
                return;
            }

            selectedGame.get().runGame();
        }

    }

}
