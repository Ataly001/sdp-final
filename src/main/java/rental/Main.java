package rental;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import rental.abylai.PricingMenu;
import rental.abylai.PricingService;
import rental.ataly.CatalogMenu;
import rental.ataly.RentalFacade;
import rental.nazar.CustomerNotifier;
import rental.nazar.RentalLog;
import rental.nazar.RentalMenu;
import rental.nazar.RentalService;

public final class Main {
    public static void main(String[] args) {
        run(new Scanner(System.in), System.out);
    }

    static void run(Scanner scanner, PrintStream output) {
        RentalLog log = new RentalLog();
        RentalService service = new RentalService();
        service.subscribe(new CustomerNotifier(output));
        service.subscribe(log);

        RentalFacade facade = new RentalFacade(new PricingService(), service);
        facade.addVehicle("1", "CAR-01", "Toyota Corolla");
        facade.addVehicle("2", "BIKE-01", "Giant Escape");
        facade.addVehicle("3", "VAN-01", "Ford Transit");

        ConsoleInput input = new ConsoleInput(scanner, output);
        CatalogMenu catalog = new CatalogMenu(facade, input);
        PricingMenu pricing = new PricingMenu(facade, input);
        RentalMenu rental = new RentalMenu(facade, pricing, log, input);

        output.println("Vehicle Rental System");
        output.println("1 List | 2 Add | 3 Quote | 4 Rent | 5 Return | 6 History | 0 Exit");
        while (scanner.hasNextLine()) {
            try {
                switch (input.ask("Action")) {
                    case "1" -> catalog.list();
                    case "2" -> catalog.add();
                    case "3" -> pricing.quoteOnly();
                    case "4" -> rental.rent();
                    case "5" -> rental.returnVehicle();
                    case "6" -> rental.history();
                    case "0" -> { return; }
                    default -> output.println("Choose an action from 0 to 6.");
                }
            } catch (NumberFormatException exception) {
                output.println("Error: Duration must be a whole number.");
            } catch (IllegalArgumentException | IllegalStateException exception) {
                output.println("Error: " + exception.getMessage());
            } catch (NoSuchElementException exception) {
                return;
            }
        }
    }
}
