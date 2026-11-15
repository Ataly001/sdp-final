package rental.nazar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RentalService {
    private final Map<String, RentalSlot> slots = new HashMap<>();
    private final List<RentalObserver> observers = new ArrayList<>();

    public void subscribe(RentalObserver observer) {
        observers.add(observer);
    }

    public boolean isAvailable(String id) {
        RentalSlot slot = slots.get(id);
        return slot == null || slot.isAvailable();
    }

    public void rent(String id, String customer, int total) {
        if (customer == null || customer.isBlank()) {
            throw new IllegalArgumentException("Enter a customer name.");
        }
        Rental rental = new Rental(customer.strip(), total);
        RentalSlot slot = slots.get(id);
        if (slot == null) {
            slot = new RentalSlot();
            slots.put(id, slot);
        }
        slot.rent(rental);
        publish(new RentalEvent("RENTED", id, rental.customer(), rental.total()));
    }

    public void returnVehicle(String id) {
        RentalSlot slot = slots.get(id);
        if (slot == null) {
            throw new IllegalStateException("Vehicle is already available.");
        }
        Rental rental = slot.returnVehicle();
        publish(new RentalEvent("RETURNED", id, rental.customer(), rental.total()));
    }

    private void publish(RentalEvent event) {
        for (RentalObserver observer : observers) {
            observer.onEvent(event);
        }
    }
}
