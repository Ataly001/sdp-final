package rental.nazar;

final class Rented implements RentalState {
    private final Rental rental;

    Rented(Rental rental) {
        this.rental = rental;
    }

    public void rent(RentalSlot slot, Rental rental) {
        throw new IllegalStateException("Vehicle is already rented.");
    }

    public Rental returnVehicle(RentalSlot slot) {
        slot.changeState(new Available());
        return rental;
    }

    public boolean isAvailable() { return false; }
}
