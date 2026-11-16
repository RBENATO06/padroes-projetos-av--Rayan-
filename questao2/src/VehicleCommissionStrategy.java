public class VehicleCommissionStrategy implements CommissionStrategy {
    @Override
    public double calculate(double salePrice) {
        return Math.min(salePrice * 0.08, 5000.00);
    }
}
