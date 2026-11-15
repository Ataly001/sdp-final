package rental.ataly;

import rental.abylai.PricingService;
import rental.nazar.RentalService;
import static rental.Checks.*;

public final class CatalogTests {
    public static void run() {
        eachFactoryCreatesItsVehicle();
        System.out.println("PASS eachFactoryCreatesItsVehicle");
        catalogRejectsDuplicateId();
        System.out.println("PASS catalogRejectsDuplicateId");
        facadeRejectsUnknownId();
        System.out.println("PASS facadeRejectsUnknownId");
        quoteLeavesVehicleAvailable();
        System.out.println("PASS quoteLeavesVehicleAvailable");
    }

    private static void eachFactoryCreatesItsVehicle() {
        RentalFacade facade = new RentalFacade(new PricingService(), new RentalService());
        equal(Car.class, facade.addVehicle("1", "C", "Toyota").getClass());
        equal(Bike.class, facade.addVehicle("2", "B", "Giant").getClass());
        equal(Van.class, facade.addVehicle("3", "V", "Ford").getClass());
        equal(3, facade.vehicles().size());
    }

    private static void catalogRejectsDuplicateId() {
        RentalFacade facade = new RentalFacade(new PricingService(), new RentalService());
        facade.addVehicle("1", "C", "Toyota");
        fails(IllegalArgumentException.class, () -> facade.addVehicle("2", " C ", "Giant"));
        equal(1, facade.vehicles().size());
    }

    private static void facadeRejectsUnknownId() {
        RentalFacade facade = new RentalFacade(new PricingService(), new RentalService());
        fails(IllegalArgumentException.class, () -> facade.quote("X", "1", 2, false, false));
        fails(IllegalArgumentException.class, () -> facade.returnVehicle("X"));
    }

    private static void quoteLeavesVehicleAvailable() {
        RentalFacade facade = new RentalFacade(new PricingService(), new RentalService());
        facade.addVehicle("1", "C", "Toyota");
        equal(5000, facade.quote("C", "1", 2, false, false).total());
        equal(true, facade.isAvailable("C"));
    }
}
