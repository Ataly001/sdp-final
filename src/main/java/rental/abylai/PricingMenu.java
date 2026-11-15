package rental.abylai;

import rental.ConsoleInput;
import rental.ataly.RentalFacade;

public final class PricingMenu {
    private final RentalFacade facade;
    private final ConsoleInput input;

    public PricingMenu(RentalFacade facade, ConsoleInput input) {
        this.facade = facade;
        this.input = input;
    }

    public void quoteOnly() {
        show(readRequest().quote());
    }

    public Request readRequest() {
        String id = input.ask("Vehicle ID");
        String rate = input.ask("Tariff (1 hourly, 2 daily)");
        int duration = Integer.parseInt(input.ask("Duration"));
        boolean insurance = input.yes("Add insurance?");
        boolean delivery = input.yes("Add delivery?");
        Quote quote = facade.quote(id, rate, duration, insurance, delivery);
        return new Request(id, rate, duration, insurance, delivery, quote);
    }

    public void show(Quote quote) {
        for (String line : quote.lines()) {
            input.out().println(line);
        }
        input.out().println("Total: " + quote.total() + " KZT");
    }

    public record Request(String id, String rate, int duration,
                          boolean insurance, boolean delivery, Quote quote) {}
}
