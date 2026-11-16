public class RealEstateCommissionStrategy implements CommissionStrategy {
    @Override
    public double calculate(double salePrice) {
        return (salePrice * 0.03) + 1500.00;
    }
}
