package dev.saathoff.io.input.impl;

import dev.saathoff.grid.data.Coordinate;
import dev.saathoff.io.input.AbstractInputService;
import dev.saathoff.io.input.InputSource;
import dev.saathoff.io.input.converter.impl.CoordinateConverter;
import dev.saathoff.io.input.validator.criteria.CoordinateValidationCriteria;
import dev.saathoff.io.input.validator.impl.CoordinateValidator;
import dev.saathoff.io.output.OutputService;

public class CoordinateInput extends AbstractInputService<Coordinate, CoordinateValidationCriteria> {


    public CoordinateInput(OutputService outputService, InputSource inputSource) {
        super(outputService, inputSource, new CoordinateConverter(), new CoordinateValidator());
    }

}
