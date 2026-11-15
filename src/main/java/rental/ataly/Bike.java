package rental.ataly;

public final class Bike extends Vehicle {
    public Bike(String id, String model) {
        super(id, model);
    }

    public String type() { return "Bike"; }
    public int hourlyRate() { return 800; }
    public int dailyRate() { return 4000; }
}