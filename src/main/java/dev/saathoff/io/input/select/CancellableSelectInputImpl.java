package dev.saathoff.io.input.select;

import dev.saathoff.io.input.Input;
import dev.saathoff.io.output.OutputService;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class CancellableSelectInputImpl extends AbstractSelectInput implements CancellableSelectInput {

    private Input<Optional<Integer>, Collection<Integer>> cancellableIntegerSelect;

    public CancellableSelectInputImpl(OutputService outputService, Input<Optional<Integer>, Collection<Integer>> cancellableIntegerSelect) {
        super(outputService);
        this.cancellableIntegerSelect = cancellableIntegerSelect;
    }

    @Override
    public <T extends Selectable> Optional<T> select(String label, Map<Integer, T> options) {
        super.outputService.output("\n--- " + label + " ---");
        super.listOptions(options);
        Optional<T> optionalSelection =
                cancellableIntegerSelect.getInput("Select by typing the number in front of your desired option: ", options.keySet())
                        .map(options::get);

        return optionalSelection;
    }

    @Override
    public <T extends Enum<T> & Selectable> Optional<T> selectFromEnum(String label, Class<T> enumClass) {
        return select(label, enumToMap(enumClass));
    }
}
