package rental.ataly;

final class BikeFactory extends VehicleFactory {
    @Override
    protected Vehicle createVehicle(String id, String model) {
        return new Bike(id, model);
    }
}
