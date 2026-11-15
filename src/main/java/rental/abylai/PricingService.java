package rental.abylai;

import rental.ataly.Vehicle;

public final class PricingService {
    public Quote quote(Vehicle vehicle, String rate, int duration,
                       boolean insurance, boolean delivery) {
        if (duration < 1 || duration > 365) {
            throw new IllegalArgumentException("Duration must be from 1 to 365.");
        }

        PricingStrategy strategy;
        String unit;
        switch (rate) {
            case "1" -> {
                strategy = new HourlyPricing();
                unit = "hour(s)";
            }
            case "2" -> {
                strategy = new DailyPricing();
                unit = "day(s)";
            }
            default -> throw new IllegalArgumentException("Choose tariff 1 or 2.");
        }

        PriceComponent price = new BasePrice(strategy, vehicle, duration, unit);
        if (insurance) {
            price = new InsurancePrice(price);
        }
        if (delivery) {
            price = new DeliveryPrice(price);
        }
        return price.calculate();
    }
}
