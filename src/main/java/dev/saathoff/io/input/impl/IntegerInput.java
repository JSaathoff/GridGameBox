package dev.saathoff.io.input.impl;

import dev.saathoff.io.input.AbstractInputService;
import dev.saathoff.io.input.InputSource;
import dev.saathoff.io.input.converter.impl.IntegerInputConverter;
import dev.saathoff.io.input.validator.criteria.RangeValidationCriteria;
import dev.saathoff.io.input.validator.impl.IntegerValidator;
import dev.saathoff.io.output.OutputService;

public class IntegerInput extends AbstractInputService<Integer, RangeValidationCriteria> {

    public IntegerInput(OutputService outputService, InputSource inputSource) {
        super(outputService, inputSource, new IntegerInputConverter(), new IntegerValidator());
    }

}

