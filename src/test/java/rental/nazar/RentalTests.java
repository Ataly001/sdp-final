package rental.nazar;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static rental.Checks.*;

public final class RentalTests {
    public static void run() {
        rentReturnAndRentAgain();
        System.out.println("PASS rentReturnAndRentAgain");
        doubleRentalDoesNotPublish();
        System.out.println("PASS doubleRentalDoesNotPublish");
        invalidReturnAndBlankNameAreRejected();
        System.out.println("PASS invalidReturnAndBlankNameAreRejected");
        bothObserversReceiveBothEvents();
        System.out.println("PASS bothObserversReceiveBothEvents");
    }

    private static void rentReturnAndRentAgain() {
        RentalService service = new RentalService();
        equal(true, service.isAvailable("C"));
        service.rent("C", "Ataly", 5000);
        equal(false, service.isAvailable("C"));
        equal(true, service.isAvailable("B"));
        service.returnVehicle("C");
        equal(true, service.isAvailable("C"));
        service.rent("C", "Nazar", 7000);
        equal(false, service.isAvailable("C"));
    }

    private static void doubleRentalDoesNotPublish() {
        RentalService service = new RentalService();
        RentalLog log = new RentalLog();
        service.subscribe(log);
        service.rent("C", "Ataly", 5000);
        fails(IllegalStateException.class, () -> service.rent("C", "Nazar", 100));
        service.returnVehicle("C");
        equal(2, log.events().size());
        equal(new RentalEvent("RETURNED", "C", "Ataly", 5000), log.events().get(1));
    }

    private static void invalidReturnAndBlankNameAreRejected() {
        RentalService service = new RentalService();
        RentalLog log = new RentalLog();
        service.subscribe(log);
        fails(IllegalStateException.class, () -> service.returnVehicle("C"));
        fails(IllegalArgumentException.class, () -> service.rent("C", " ", 100));
        equal(true, service.isAvailable("C"));
        equal(0, log.events().size());
    }

    private static void bothObserversReceiveBothEvents() {
        RentalService service = new RentalService();
        RentalLog log = new RentalLog();
        ByteArrayOutputStream console = new ByteArrayOutputStream();
        service.subscribe(log);
        service.subscribe(new CustomerNotifier(new PrintStream(console)));
        service.rent("C", "Ataly", 5000);
        service.returnVehicle("C");
        equal(2, log.events().size());
        equal(true, console.toString().contains("Ataly: C RENTED."));
        equal(true, console.toString().contains("Ataly: C RETURNED."));
    }
}
