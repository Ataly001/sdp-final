package rental.ataly;

import java.util.*;

final class VehicleCatalog {
    private final Map<String, Vehicle> vehicles = new LinkedHashMap<>();

    void add(Vehicle vehicle) {
        if (vehicles.containsKey(vehicle.id())) {
            throw new IllegalArgumentException("Vehicle ID already exists.");
        }
        vehicles.put(vehicle.id(), vehicle);
    }

    Vehicle find(String id) {
        Vehicle vehicle = vehicles.get(id.strip());
        if (vehicle == null) {
            throw new IllegalArgumentException("Unknown vehicle: " + id);
        }
        return vehicle;
    }

    List<Vehicle> list() {
        return new ArrayList<>(vehicles.values());
    }
}
