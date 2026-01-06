package dev.saathoff.io.input;

import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.grid.data.Grid;
import dev.saathoff.io.output.OutputService;

import java.util.Scanner;

public class ConsoleCoordinateInput implements CoordinateInput{

    private Scanner scanner;

    private OutputService outputService;

    public ConsoleCoordinateInput(Scanner scanner, OutputService outputService) {
        this.scanner = scanner;
        this.outputService = outputService;
    }

    @Override
    public Coordinate getCoordinate(String label, Grid<?> grid) {
        outputService.output("\n--- " + label + " ---");
        outputService.output("Enter row");
        int row = getCoordinate(grid.getRowCount());
        outputService.output("Enter column");
        int column = getCoordinate(grid.getColumnCount());
        return new Coordinate(row, column);
    }

    private int getCoordinate(int maxValue) {
        int coord;
        while (true) {
            String input = scanner.next();
            try {
                coord = Integer.parseInt(input);
                if (coord >= 0 && coord <= maxValue) {
                    return coord;
                }
                outputService.output("Invalid choice. Please pick a number from the list.");
            } catch (NumberFormatException e) {
                outputService.output("Invalid input. Please enter a number.");
            }
        }
    }
}
