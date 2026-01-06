package dev.saathoff.io.input;

import dev.saathoff.io.output.OutputService;

import java.util.Scanner;


public class NumberInput extends AbstractInputService<Integer, NumberValidationObject> {


    public NumberInput(OutputService outputService, Scanner scanner) {
        super(outputService, scanner);
    }

    @Override
    protected ValidationResult validate(Integer input, NumberValidationObject validationObject) throws RuntimeException {
        if(input < validationObject.min()){
            return ValidationResult.fail("Number has to be bigger than " + validationObject.min());
        }
        if( input > validationObject.max()){
           return ValidationResult.fail("Number has to be lower than " + validationObject.max());
        }
        return ValidationResult.ok();
    }

    @Override
    protected Integer handleInput() {
        super.outputService.output("Enter Number:");
        int number;
        while (true) {
            String input = super.scanner.next();
            try {
                number = Integer.parseInt(input);
                return  number;
            } catch (NumberFormatException e) {
                super.outputService.output("Invalid input. Please enter a number.");
            }
        }
    }
}

