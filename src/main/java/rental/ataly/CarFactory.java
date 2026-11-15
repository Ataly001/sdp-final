package rental.ataly;

final class CarFactory extends VehicleFactory {
    @Override
    protected Vehicle createVehicle(String id, String model) {
        return new Car(id, model);
    }
}