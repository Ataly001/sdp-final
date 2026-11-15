package rental.ataly;

import java.util.List;
import rental.abylai.PricingService;
import rental.abylai.Quote;
import rental.nazar.RentalService;

public final class RentalFacade {
    private final VehicleCatalog catalog = new VehicleCatalog();
    private final PricingService pricing;
    private final RentalService rentals;

    public RentalFacade(PricingService pricing, RentalService rentals) {
        this.pricing = pricing;
        this.rentals = rentals;
    }

    public Vehicle addVehicle(String type, String id, String model) {
        VehicleFactory factory = switch (type) {
            case "1" -> new CarFactory();
            case "2" -> new BikeFactory();
            case "3" -> new VanFactory();
            default -> throw new IllegalArgumentException("Choose vehicle type 1, 2 or 3.");
        };
        return factory.addTo(catalog, id, model);
    }

    public List<Vehicle> vehicles() {
        return catalog.list();
    }

    public boolean isAvailable(String id) {
        return rentals.isAvailable(catalog.find(id).id());
    }

    public Quote quote(String id, String rate, int duration, boolean insurance, boolean delivery) {
        return pricing.quote(catalog.find(id), rate, duration, insurance, delivery);
    }

    public Quote rent(String id, String customer, String rate, int duration,
                      boolean insurance, boolean delivery) {
        Vehicle vehicle = catalog.find(id);
        Quote quote = pricing.quote(vehicle, rate, duration, insurance, delivery);
        rentals.rent(vehicle.id(), customer, quote.total());
        return quote;
    }

    public void returnVehicle(String id) {
        rentals.returnVehicle(catalog.find(id).id());
    }
}
