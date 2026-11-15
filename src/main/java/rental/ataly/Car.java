package rental.ataly;

public final class Car extends Vehicle {
    public Car(String id, String model) {
        super(id, model);
    }

    public String type() { return "Car"; }
    public int hourlyRate() { return 2500; }
    public int dailyRate() { return 15000; }
}
