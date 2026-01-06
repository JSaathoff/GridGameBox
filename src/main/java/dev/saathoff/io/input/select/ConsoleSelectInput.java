package dev.saathoff.io.input.select;

import dev.saathoff.io.output.OutputService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleSelectInput implements SelectInput {

    private Scanner scanner;

    private OutputService outputService;

    public ConsoleSelectInput(Scanner scanner, OutputService outputService) {
        this.scanner = scanner;
        this.outputService = outputService;
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
        outputService.output("\n--- " + label + " ---");
        this.listOptions(options);
        outputService.output("Select by typing the number in front of your desired option");
        int selection;
        while (true) {
            outputService.output("Selection: ");
            String input = scanner.next();
            try {
                selection = Integer.parseInt(input);
                if (options.containsKey(selection)) {
                    return options.get(selection);
                }
                outputService.output("Invalid choice. Please pick a number from the list.");
            } catch (NumberFormatException e) {
                outputService.output("Invalid input. Please enter a number.");
            }
        }
    }

    private <T extends Selectable> void listOptions(Map<Integer, T> options) {
        options.forEach((key, value) ->
                outputService.output("[" + key + "] - " + value.getLabelForSelection())
        );
    }

}
