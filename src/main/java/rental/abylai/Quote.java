package rental.abylai;

import java.util.ArrayList;
import java.util.List;

public final class Quote {
    private final List<String> lines = new ArrayList<>();
    private int total;

    void add(String label, int amount) {
        lines.add(label + ": " + amount + " KZT");
        total += amount;
    }

    public int total() {
        return total;
    }

    public List<String> lines() {
        return List.copyOf(lines);
    }
}
