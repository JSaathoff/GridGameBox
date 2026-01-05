package dev.saathoff.io.output;

public class ConsoleOutputService implements OutputService {

    @Override
    public void output(String string) {
        System.out.println(string);
    }
}
