package rental.ataly;

import rental.ConsoleInput;

public final class CatalogMenu {
    private final RentalFacade facade;
    private final ConsoleInput input;

    public CatalogMenu(RentalFacade facade, ConsoleInput input) {
        this.facade = facade;
        this.input = input;
    }

    public void add() {
        String type = input.ask("Type (1 Car, 2 Bike, 3 Van)");
        String id = input.ask("Vehicle ID");
        String model = input.ask("Model");
        Vehicle vehicle = facade.addVehicle(type, id, model);
        input.out().println("Added: " + vehicle.id() + " (" + vehicle.type() + ")");
    }

    public void list() {
        for (Vehicle vehicle : facade.vehicles()) {
            String status = facade.isAvailable(vehicle.id()) ? "Available" : "Rented";
            input.out().printf("%s | %s | %s | %s%n",
                    vehicle.id(), vehicle.type(), vehicle.model(), status);
        }
    }
}