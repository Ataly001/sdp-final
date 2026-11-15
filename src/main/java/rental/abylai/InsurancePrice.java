package rental.abylai;

final class InsurancePrice implements PriceComponent {
    private final PriceComponent wrapped;

    InsurancePrice(PriceComponent wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Quote calculate() {
        Quote quote = wrapped.calculate();
        quote.add("Insurance", 3000);
        return quote;
    }
}
