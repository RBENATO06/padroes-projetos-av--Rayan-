import java.util.List;

public class AuthenticityCertificateDecorator extends AdditionalServiceDecorator {
    private static final double FIXED_COST = 300.00;

    public AuthenticityCertificateDecorator(InvoiceComponent wrapped) {
        super(wrapped);
    }

    @Override
    public double getTotalCost() {
        return super.getTotalCost() + FIXED_COST;
    }

    @Override
    public List<String> getServiceDescriptions() {
        List<String> descriptions = super.getServiceDescriptions();
        descriptions.add(String.format("Certificado de autenticidade: R$ %.2f", FIXED_COST));
        return descriptions;
    }
}
