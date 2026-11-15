package rental.nazar;

interface RentalState {
    void rent(RentalSlot slot, Rental rental);
    Rental returnVehicle(RentalSlot slot);
    boolean isAvailable();
}
