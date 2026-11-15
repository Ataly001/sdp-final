package rental.nazar;

import java.io.PrintStream;

public final class CustomerNotifier implements RentalObserver {
    private final PrintStream output;

    public CustomerNotifier(PrintStream output) {
        this.output = output;
    }

    @Override
    public void onEvent(RentalEvent event) {
        output.println("Notification for " + event.customer() + ": " +
                event.vehicleId() + " " + event.action() + ".");
    }
}
