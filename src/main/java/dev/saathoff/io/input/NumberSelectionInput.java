package dev.saathoff.io.input;

import dev.saathoff.io.input.converter.impl.IntegerInputConverter;
import dev.saathoff.io.input.validator.impl.ContainedInCollectionValidator;
import dev.saathoff.io.output.OutputService;

import java.util.Collection;

public class NumberSelectionInput extends AbstractInputService<Integer, Collection<Integer>> {
    public NumberSelectionInput(OutputService outputService, InputSource inputSource) {
        super(outputService, inputSource, new IntegerInputConverter(), new ContainedInCollectionValidator());
    }
}
