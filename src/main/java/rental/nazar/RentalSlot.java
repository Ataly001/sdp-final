package rental.nazar;

final class RentalSlot {
    private RentalState state = new Available();

    void rent(Rental rental) {
        state.rent(this, rental);
    }

    Rental returnVehicle() {
        return state.returnVehicle(this);
    }

    boolean isAvailable() { return state.isAvailable(); }

    void changeState(RentalState state) {
        this.state = state;
    }
}
