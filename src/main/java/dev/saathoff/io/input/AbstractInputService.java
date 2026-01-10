package dev.saathoff.io.input;

import dev.saathoff.io.input.converter.InputConverter;
import dev.saathoff.io.input.validator.InputValidator;
import dev.saathoff.io.input.validator.ValidationResult;
import dev.saathoff.io.output.OutputService;

/**
 * Base implementation for a looped input process.
 *
 * @param <I> The <b>Result Type</b>: The type of data returned after successful conversion.
 * @param <V> The <b>Validation Type</b>: The object providing the context needed to validate the converted input.
 */
public abstract class AbstractInputService<I, V> implements Input<I, V> {

    protected final OutputService outputService;
    protected final InputSource inputSource;
    protected final InputConverter<I> converter;
    protected final InputValidator<I, V> validator;

    protected AbstractInputService(OutputService outputService, InputSource inputSource, InputConverter<I> converter, InputValidator<I, V> validator) {
        this.outputService = outputService;
        this.inputSource = inputSource;
        this.converter = converter;
        this.validator = validator;
    }

    @Override
    public final I getInput(String label, V validationCriteria) {
        while (true) {
            outputService.output(label);
            String raw = inputSource.readInput();

            try {
                I convertedValue = converter.convert(raw);

                ValidationResult result = validator.validate(convertedValue, validationCriteria);

                if (result.isValid()) {
                    return convertedValue;
                }
                
                outputService.output(result.errorMessage());

            } catch (IllegalArgumentException e) {
                outputService.output("Invalid format: " + e.getMessage());
            }
        }
    }
}
