package rental.ataly;

abstract class VehicleFactory {
    public Vehicle addTo(VehicleCatalog catalog, String id, String model) {
        Vehicle vehicle = createVehicle(id, model);
        catalog.add(vehicle);
        return vehicle;
    }

    protected abstract Vehicle createVehicle(String id, String model);
}
