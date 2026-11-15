package rental.abylai;

import rental.ataly.Vehicle;

final class HourlyPricing implements PricingStrategy {
    @Override
    public int calculate(Vehicle vehicle, int duration) {

        return vehicle.hourlyRate() * duration;
    }
}
