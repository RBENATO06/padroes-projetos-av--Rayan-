import java.util.List;

public class SpecialPackagingDecorator extends AdditionalServiceDecorator {
    private static final double FIXED_COST = 180.00;

    public SpecialPackagingDecorator(InvoiceComponent wrapped) {
        super(wrapped);
    }

    @Override
    public double getTotalCost() {
        return super.getTotalCost() + FIXED_COST;
    }

    @Override
    public List<String> getServiceDescriptions() {
        List<String> descriptions = super.getServiceDescriptions();
        descriptions.add(String.format("Embalagem especial: R$ %.2f", FIXED_COST));
        return descriptions;
    }
}
