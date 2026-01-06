package dev.saathoff.io.input;

import dev.saathoff.io.output.OutputService;

import java.util.Scanner;

public abstract class AbstractInputService <I, V> {

    protected OutputService outputService;

    protected Scanner scanner;

    protected AbstractInputService(OutputService outputService, Scanner scanner) {
        this.outputService = outputService;
        this.scanner = scanner;
    }

    public I getInput(String label, V validationObject){
        while (true){
            outputService.output(label);
            I input = handleInput();
            ValidationResult result = this.validate(input, validationObject);
            if(result.isValid()){
                return input;
            }
            outputService.output(result.errorMessage());
        }


    }

    protected abstract ValidationResult validate(I input, V validationObject);

    protected abstract I handleInput();
}
