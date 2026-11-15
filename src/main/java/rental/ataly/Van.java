package rental.ataly;

public final class Van extends Vehicle {
    public Van(String id, String model) {
        super(id, model);
    }

    public String type() { return "Van"; }
    public int hourlyRate() { return 4000; }
    public int dailyRate() { return 24000; }
}
