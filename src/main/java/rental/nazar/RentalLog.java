package rental.nazar;

import java.util.ArrayList;
import java.util.List;

public final class RentalLog implements RentalObserver {
    private final List<RentalEvent> events = new ArrayList<>();

    @Override
    public void onEvent(RentalEvent event) {
        events.add(event);
    }

    public List<RentalEvent> events() {
        return List.copyOf(events);
    }
}
