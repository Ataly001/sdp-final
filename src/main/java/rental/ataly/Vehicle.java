package rental.ataly;

public abstract class Vehicle {
    private final String id;
    private final String model;

    protected Vehicle(String id, String model) {
        if (id == null || id.isBlank() || model == null || model.isBlank()) {
            throw new IllegalArgumentException("Enter a vehicle ID and model.");
        }
        this.id = id.strip();
        this.model = model.strip();
    }

    public String id() { return id; }
    public String model() { return model; }
    public abstract String type();
    public abstract int hourlyRate();
    public abstract int dailyRate();
}
