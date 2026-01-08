package dev.saathoff.io.input.select;

import dev.saathoff.io.input.NumberSelectionInput;
import dev.saathoff.io.output.OutputService;

import java.util.HashMap;
import java.util.Map;

public class SelectInputImpl implements SelectInput {


    private OutputService outputService;
    private final NumberSelectionInput integerInput;

    public SelectInputImpl(OutputService outputService, NumberSelectionInput integerInput) {
        this.outputService = outputService;
        this.integerInput = integerInput;
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
        int selection = integerInput.getInput("Select by typing the number in front of your desired option: ", options.keySet());
        return options.get(selection);
    }

    private <T extends Selectable> void listOptions(Map<Integer, T> options) {
        options.forEach((key, value) ->
                outputService.output("[" + key + "] - " + value.getLabelForSelection())
        );
    }

}
