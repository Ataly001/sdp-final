package rental;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static rental.Checks.*;

public final class ConsoleTests {
    public static void run() {
        fullConsoleScenario();
        System.out.println("PASS fullConsoleScenario");
        cancelAndBadInputLeaveRentalUntouched();
        System.out.println("PASS cancelAndBadInputLeaveRentalUntouched");
    }

    private static void fullConsoleScenario() {
        String actions = String.join("\n", "2", "3", "V-02", "Sprinter", "4", "CAR-01",
                "2", "2", "y", "y", "y", "Ataly", "1", "4", "CAR-01",
                "1", "2", "n", "n", "y", "Nazar", "5", "CAR-01", "6", "1", "0");
        ByteArrayOutputStream console = new ByteArrayOutputStream();
        Main.run(new Scanner(actions), new PrintStream(console));
        String output = console.toString();
        equal(true, output.contains("Added: V-02 (Van)"));
        equal(true, output.contains("Total: 35000 KZT"));
        equal(true, output.contains("Toyota Corolla | Rented"));
        equal(true, output.contains("Error: Vehicle is already rented."));
        equal(true, output.contains("RENTED | CAR-01 | Ataly | 35000 KZT"));
        equal(true, output.contains("RETURNED | CAR-01 | Ataly | 35000 KZT"));
        equal(true, output.contains("Toyota Corolla | Available"));
        equal(false, output.contains("Notification for Nazar"));
    }

    private static void cancelAndBadInputLeaveRentalUntouched() {
        String actions = String.join("\n", "3", "CAR-01", "1", "abc", "4",
                "CAR-01", "2", "2", "y", "n", "n", "6", "0");
        ByteArrayOutputStream console = new ByteArrayOutputStream();
        Main.run(new Scanner(actions), new PrintStream(console));
        String output = console.toString();
        equal(true, output.contains("Duration must be a whole number."));
        equal(true, output.contains("Rental cancelled."));
        equal(true, output.contains("No rental events yet."));
        equal(false, output.contains("Notification for"));
    }
}
