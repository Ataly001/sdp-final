package rental.abylai;

import rental.ataly.Vehicle;

interface PricingStrategy {
    int calculate(Vehicle vehicle, int duration);
}
