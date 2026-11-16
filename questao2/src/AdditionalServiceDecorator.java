import java.util.List;

public abstract class AdditionalServiceDecorator implements InvoiceComponent {
    protected final InvoiceComponent wrapped;

    protected AdditionalServiceDecorator(InvoiceComponent wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String getLotName() {
        return wrapped.getLotName();
    }

    @Override
    public double getSalePrice() {
        return wrapped.getSalePrice();
    }

    @Override
    public double getTotalCost() {
        return wrapped.getTotalCost();
    }

    @Override
    public List<String> getServiceDescriptions() {
        return wrapped.getServiceDescriptions();
    }
}
