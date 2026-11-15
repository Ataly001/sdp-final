package rental.abylai;

import rental.ataly.Vehicle;

final class DailyPricing implements PricingStrategy {
    @Override
    public int calculate(Vehicle vehicle, int duration) {

        return vehicle.dailyRate() * duration;
    }
}
