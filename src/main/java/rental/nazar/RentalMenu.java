package rental.nazar;

import rental.ConsoleInput;
import rental.abylai.PricingMenu;
import rental.ataly.RentalFacade;

public final class RentalMenu {
    private final RentalFacade facade;
    private final PricingMenu pricing;
    private final RentalLog log;
    private final ConsoleInput input;

    public RentalMenu(RentalFacade facade, PricingMenu pricing, RentalLog log, ConsoleInput input) {
        this.facade = facade;
        this.pricing = pricing;
        this.log = log;
        this.input = input;
    }

    public void rent() {
        PricingMenu.Request request = pricing.readRequest();
        pricing.show(request.quote());
        if (!input.yes("Confirm rental?")) {
            input.out().println("Rental cancelled.");
            return;
        }
        String customer = input.ask("Customer name");
        facade.rent(request.id(), customer, request.rate(), request.duration(),
                request.insurance(), request.delivery());
        input.out().println("Rental confirmed.");
    }

    public void returnVehicle() {
        facade.returnVehicle(input.ask("Vehicle ID"));
        input.out().println("Vehicle returned.");
    }

    public void history() {
        if (log.events().isEmpty()) {
            input.out().println("No rental events yet.");
        }
        for (RentalEvent event : log.events()) {
            input.out().printf("%s | %s | %s | %d KZT%n",
                    event.action(), event.vehicleId(), event.customer(), event.total());
        }
    }
}
