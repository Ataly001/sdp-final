package rental;

import rental.ataly.CatalogTests;
import rental.abylai.PricingTests;
import rental.nazar.RentalTests;

public final class AllTests {
    public static void main(String[] args) {
        CatalogTests.run();
        PricingTests.run();
        RentalTests.run();
        ConsoleTests.run();
        System.out.println("14 tests passed.");
    }
}
