package dev.saathoff.io.input;

import dev.saathoff.io.input.converter.InputConverter;
import dev.saathoff.io.input.validator.InputValidator;
import dev.saathoff.io.input.validator.ValidationResult;
import dev.saathoff.io.output.OutputService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AbstractInputServiceTest {

    @Mock
    private OutputService outputService;
    @Mock
    private InputSource inputSource;
    @Mock
    private InputConverter<Integer> converter;
    @Mock
    private InputValidator<Integer, String> validator;

    private AbstractInputService<Integer, String> input;

    private static class TestInputService extends AbstractInputService<Integer, String> {
        protected TestInputService(OutputService os, InputSource is, InputConverter<Integer> c, InputValidator<Integer, String> v) {
            super(os, is, c, v);
        }
    }

    @BeforeEach
    void setUp() {
        input = new TestInputService(outputService, inputSource, converter, validator);
    }

    @Test
    void getInput_ShouldReturnConvertedValue_WhenValidationSucceedsFirstTime() {
        // GIVEN
        String label = "Enter age:";
        String rawInput = "25";
        Integer convertedValue = 25;
        String criteria = "";

        when(inputSource.readInput()).thenReturn(rawInput);
        when(converter.convert(rawInput)).thenReturn(convertedValue);
        when(validator.validate(convertedValue, criteria)).thenReturn(new ValidationResult(true, null));

        // WHEN
        Integer result = input.getInput(label, criteria);

        // THEN
        assertEquals(convertedValue, result);
        verify(outputService).output(label);
        verify(inputSource).readInput();
    }

    @Test
    void getInput_ShouldHandleConverterException() {
        // GIVEN
        String label = "Enter number:";
        when(inputSource.readInput()).thenReturn("abc", "123");

        when(converter.convert("abc")).thenThrow(new IllegalArgumentException("Not a number"));
        when(converter.convert("123")).thenReturn(123);

        when(validator.validate(123, null)).thenReturn(new ValidationResult(true, null));

        // WHEN
        Integer result = input.getInput(label, null);

        // THEN
        assertEquals(123, result);
        verify(outputService).output("Invalid format: Not a number");
    }

    @Test
    void getInput_ShouldRetry_WhenValidationFailsThenSucceeds() {
        // GIVEN
        String label = "Enter age:";
        String criteria = "positive";

        when(inputSource.readInput()).thenReturn("invalid", "10");
        when(converter.convert("invalid")).thenReturn(-5);
        when(converter.convert("10")).thenReturn(10);

        when(validator.validate(-5, criteria)).thenReturn(new ValidationResult(false, "Must be positive"));
        when(validator.validate(10, criteria)).thenReturn(new ValidationResult(true, null));

        // WHEN
        Integer result = input.getInput(label, criteria);

        // THEN
        assertEquals(10, result);
        verify(outputService).output("Must be positive");
        verify(outputService, times(2)).output(label);
    }

}