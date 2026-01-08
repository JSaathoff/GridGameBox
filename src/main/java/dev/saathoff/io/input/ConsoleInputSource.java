package dev.saathoff.io.input;

import java.util.Scanner;

public class ConsoleInputSource implements InputSource{

    private final Scanner scanner;

    public ConsoleInputSource(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readInput() {
        return scanner.nextLine();
    }
}
