package rental.ataly;

final class VanFactory extends VehicleFactory {
    @Override
    protected Vehicle createVehicle(String id, String model) {
        return new Van(id, model);
    }
}
