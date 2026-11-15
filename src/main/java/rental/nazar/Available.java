package rental.nazar;

final class Available implements RentalState {
    public void rent(RentalSlot slot, Rental rental) {
        slot.changeState(new Rented(rental));
    }

    public Rental returnVehicle(RentalSlot slot) {
        throw new IllegalStateException("Vehicle is already available.");
    }

    public boolean isAvailable() { return true; }
}
