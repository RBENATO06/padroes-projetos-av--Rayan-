import java.util.List;

public class ExpressDeliveryDecorator extends AdditionalServiceDecorator {
    private static final double FIXED_COST = 450.00;

    public ExpressDeliveryDecorator(InvoiceComponent wrapped) {
        super(wrapped);
    }

    @Override
    public double getTotalCost() {
        return super.getTotalCost() + FIXED_COST;
    }

    @Override
    public List<String> getServiceDescriptions() {
        List<String> descriptions = super.getServiceDescriptions();
        descriptions.add(String.format("Entrega expressa: R$ %.2f", FIXED_COST));
        return descriptions;
    }
}
