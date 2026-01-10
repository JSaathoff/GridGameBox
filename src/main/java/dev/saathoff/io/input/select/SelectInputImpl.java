package dev.saathoff.io.input.select;

import dev.saathoff.io.input.Input;
import dev.saathoff.io.output.OutputService;

import java.util.Collection;
import java.util.Map;

public class SelectInputImpl extends AbstractSelectInput implements SelectInput {


    private Input<Integer, Collection<Integer>> integerInput;

    public SelectInputImpl(OutputService outputService, Input<Integer, Collection<Integer>> integerInput) {
        super(outputService);
        this.integerInput = integerInput;
    }

    @Override
    public <T extends Selectable> T select(String label, Map<Integer, T> options) {
        outputService.output("\n--- " + label + " ---");
        this.listOptions(options);
        int selection = integerInput.getInput("Select by typing the number in front of your desired option: ", options.keySet());
        return options.get(selection);
    }

    @Override
    public <T extends Enum<T> & Selectable> T selectFromEnum(String label, Class<T> enumClass) {
        return select(label, super.enumToMap(enumClass));
    }
}
