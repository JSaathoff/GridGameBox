package dev.saathoff.io.input;

import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.grid.data.Grid;

import java.util.Scanner;

public class ConsoleCoordinateInput implements CoordinateInput{

    private Scanner scanner;

    public ConsoleCoordinateInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Coordinate getCoordinate(String label, Grid<?> grid) {
        System.out.println("\n--- " + label + " ---");
        System.out.println("Enter row");
        int row = getCoordinate(grid.getRowCount());
        System.out.println("Enter column");
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
                System.out.println("Invalid choice. Please pick a number from the list.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
