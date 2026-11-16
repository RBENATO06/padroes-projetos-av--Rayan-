public class ElectronicsCommissionStrategy implements CommissionStrategy {
    @Override
    public double calculate(double salePrice) {
        return Math.max(salePrice * 0.12, 50.00);
    }
}
