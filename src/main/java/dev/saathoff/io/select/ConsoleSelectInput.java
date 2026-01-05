package dev.saathoff.io.select;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleSelectInput implements SelectInput {

    private Scanner scanner;

    public ConsoleSelectInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public <T extends Enum<T> & Selectable> T selectFromEnum(String label, Class<T> enumClass) {
        T[] constants = enumClass.getEnumConstants();
        Map<Integer, T> options = new HashMap<>();

        for (int i = 0; i < constants.length; i++) {
            options.put(i + 1, constants[i]);
        }
        return select(label, options);
    }

    @Override
    public <T extends Selectable> T select(String label, Map<Integer, T> options) {
        System.out.println("\n--- " + label + " ---");
        this.listOptions(options);
        System.out.println("Select by typing the number in front of your desired option");
        int selection = -1;
        while (true) {
            System.out.print("Selection: ");
            String input = scanner.next();
            try {
                selection = Integer.parseInt(input);
                if (options.containsKey(selection)) {
                    return options.get(selection);
                }
                System.out.println("Invalid choice. Please pick a number from the list.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private <T extends Selectable> void listOptions(Map<Integer, T> options) {
        options.forEach((key, value) ->
                System.out.println("[" + key + "] - " + value.getLabelForSelection())
        );
    }

}
