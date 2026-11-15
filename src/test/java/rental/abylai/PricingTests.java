package rental.abylai;

import rental.ataly.Car;
import rental.ataly.Vehicle;
import static rental.Checks.*;

public final class PricingTests {
    public static void run() {
        hourlyAndDailyRatesDiffer();
        System.out.println("PASS hourlyAndDailyRatesDiffer");
        addOnsWorkSeparately();
        System.out.println("PASS addOnsWorkSeparately");
        addOnsStackWithBreakdown();
        System.out.println("PASS addOnsStackWithBreakdown");
        invalidTariffAndDurationAreRejected();
        System.out.println("PASS invalidTariffAndDurationAreRejected");
    }

    private static void hourlyAndDailyRatesDiffer() {
        PricingService prices = new PricingService();
         Vehicle car = new Car("C", "Toyota");
         equal(5000, prices.quote(car, "1", 2, false, false).total());
         equal(30000, prices.quote(car, "2", 2, false, false).total());
    }

    private static void addOnsWorkSeparately() {
        PricingService prices = new PricingService();
         Vehicle car = new Car("C", "Toyota");
         equal(8000, prices.quote(car, "1", 2, true, false).total());
         equal(7000, prices.quote(car, "1", 2, false, true).total());
    }

    private static void addOnsStackWithBreakdown() {
        PricingService prices = new PricingService();
         Quote quote = prices.quote(new Car("C", "Toyota"), "2", 2, true, true);
         equal(35000, quote.total());
         equal(java.util.List.of("Base (2 day(s)): 30000 KZT",
                 "Insurance: 3000 KZT", "Delivery: 2000 KZT"), quote.lines());
    }

    private static void invalidTariffAndDurationAreRejected() {
        PricingService prices = new PricingService();
         Vehicle car = new Car("C", "Toyota");
         fails(IllegalArgumentException.class, () -> prices.quote(car, "9", 2, false, false));
         fails(IllegalArgumentException.class, () -> prices.quote(car, "1", 0, false, false));
         fails(IllegalArgumentException.class, () -> prices.quote(car, "1", 366, false, false));
    }

}
