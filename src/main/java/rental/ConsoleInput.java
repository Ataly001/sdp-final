package rental;

import java.io.PrintStream;
import java.util.Scanner;

public final class ConsoleInput {
    private final Scanner scanner;
    private final PrintStream output;

    public ConsoleInput(Scanner scanner, PrintStream output) {
        this.scanner = scanner;
        this.output = output;
    }

    public String ask(String question) {
        output.print(question + ": ");
        return scanner.nextLine().strip();
    }

    public boolean yes(String question) {
        String answer = ask(question + " (y/n)");
        if (answer.equalsIgnoreCase("y")) {
            return true;
        }
        if (answer.equalsIgnoreCase("n")) {
            return false;
        }
        throw new IllegalArgumentException("Enter y or n.");
    }

    public PrintStream out() { return output; }
}
