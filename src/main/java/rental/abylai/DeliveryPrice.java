package rental.abylai;

final class DeliveryPrice implements PriceComponent {
    private final PriceComponent wrapped;

    DeliveryPrice(PriceComponent wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Quote calculate() {
        Quote quote = wrapped.calculate();
        quote.add("Delivery", 2000);
        return quote;
    }
}
