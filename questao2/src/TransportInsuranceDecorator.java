import java.util.List;

public class TransportInsuranceDecorator extends AdditionalServiceDecorator {
    public TransportInsuranceDecorator(InvoiceComponent wrapped) {
        super(wrapped);
    }

    @Override
    public double getTotalCost() {
        return super.getTotalCost() + getServiceCost();
    }

    @Override
    public List<String> getServiceDescriptions() {
        List<String> descriptions = super.getServiceDescriptions();
        descriptions.add(String.format("Seguro de transporte (1,5%%): R$ %.2f", getServiceCost()));
        return descriptions;
    }

    private double getServiceCost() {
        return wrapped.getSalePrice() * 0.015;
    }
}
