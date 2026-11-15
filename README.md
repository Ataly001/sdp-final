# Vehicle Rental System

A Java 17 console application for renting cars, bicycles and vans. You can
add vehicles, compare hourly and daily prices, add insurance and delivery,
rent or return a vehicle, and view the rental history. Data is stored in
memory and resets when the program exits.

## Run the application

Open this folder in IntelliJ IDEA, choose JDK 17 or newer, mark
`src/main/java` as Sources Root and run `rental.Main`. For tests, also
mark `src/test/java` as Test Sources Root and run `rental.AllTests`.

From a terminal in this folder with a JDK on PATH:

| Platform | Application | Tests |
|---|---|---|
| Windows | `run.cmd` | `run.cmd test` |
| macOS / Linux | `sh run.sh` | `sh run.sh test` |

The scripts compile for Java 17. No external libraries are required.

## Who owns which code

| Student | Package in both source trees | Patterns | Scope |
|---|---|---|---|
| Аталы (Ataly) | `rental.ataly` | Factory Method, Facade | Vehicles and their factories, catalog, catalog menu, calls between subsystems |
| Абылай (Abylai) | `rental.abylai` | Strategy, Decorator | Hourly/daily calculations, add-ons, breakdown and pricing menu |
| Назар (Nazar) | `rental.nazar` | State, Observer | Rent/return, availability, notifications, event history and rental menu |

The shared `rental` package contains the entry point, console input and
integration tests. `Main` creates the services and connects all three menus.

## How the six patterns work

| Pattern | Implementation |
|---|---|
| Factory Method | `VehicleFactory.addTo()` calls `createVehicle()`. Each concrete factory overrides that method to create its own vehicle type. |
| Facade | `RentalFacade` gives the menus one place to look up a vehicle, get a quote and call the rental service. |
| Strategy | `PricingService` chooses `HourlyPricing` or `DailyPricing`. `BasePrice` calculates through the `PricingStrategy` interface. |
| Decorator | `InsurancePrice` and `DeliveryPrice` wrap a `PriceComponent`. Each delegates the base calculation and adds its own fee. |
| State | `RentalSlot` delegates to `Available` or `Rented`. The state object performs valid transitions and rejects invalid actions. |
| Observer | `RentalService` publishes successful rent/return events to `CustomerNotifier` and `RentalLog`. |

## Demo for the defense

1. Enter `1` to list the three sample vehicles. Enter `2` to add another.
2. Enter `3` to compare quotes for `CAR-01`. Two hours cost 5,000 KZT, two days cost 30,000 KZT.
3. Enter `4`, then `CAR-01`, tariff `2`, duration `2`, and `y` for both extras. The price is **35,000 KZT**. Enter `y` to confirm, then a customer name.
4. Enter `1` to see the vehicle marked Rented. Try step 3 again to verify that the system rejects a second rental.
5. Enter `5`, then `CAR-01` to return the vehicle. Enter `6` to see exactly one RENTED and one RETURNED event. Enter `1` again to check availability and `0` to exit.

The four tests per person and two console integration tests are in
`src/test/java`. The tests cover the happy path, rejected operations,
price combinations and observer delivery. Actual output is in
`docs/test-results.txt` and `docs/demo-output.txt`. The report contains
the UML and excerpts from the Java implementation.
