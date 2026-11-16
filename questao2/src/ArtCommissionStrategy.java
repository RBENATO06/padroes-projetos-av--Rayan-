public class ArtCommissionStrategy implements CommissionStrategy {
    @Override
    public double calculate(double salePrice) {
        return salePrice * 0.20;
    }
}
