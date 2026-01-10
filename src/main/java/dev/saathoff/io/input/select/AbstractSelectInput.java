package dev.saathoff.io.input.select;

import dev.saathoff.io.output.OutputService;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSelectInput {

    protected final OutputService outputService;

    protected AbstractSelectInput(OutputService outputService) {
        this.outputService = outputService;
    }

    protected <T extends Selectable> void listOptions(Map<Integer, T> options) {
        options.forEach((key, value) ->
                outputService.output(key + ": " + value.getLabelForSelection())
        );
    }

    protected <T extends Selectable> Map<Integer, T> enumToMap(Class<T> enumClass) {
        T[] constants = enumClass.getEnumConstants();
        Map<Integer, T> options = new HashMap<>();
        for (int i = 0; i < constants.length; i++) {
            options.put(i + 1, constants[i]);
        }
        return options;
    }
}
