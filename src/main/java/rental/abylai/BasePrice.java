package rental.abylai;

import rental.ataly.Vehicle;

final class BasePrice implements PriceComponent {
    private final PricingStrategy strategy;
    private final Vehicle vehicle;
    private final int duration;
    private final String unit;

    BasePrice(PricingStrategy strategy, Vehicle vehicle, int duration, String unit) {
        this.strategy = strategy;
        this.vehicle = vehicle;
        this.duration = duration;
        this.unit = unit;
    }

    @Override
    public Quote calculate() {
        Quote quote = new Quote();
        quote.add("Base (" + duration + " " + unit + ")", strategy.calculate(vehicle, duration));
        return quote;
    }
}
