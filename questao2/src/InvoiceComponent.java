import java.util.List;

public interface InvoiceComponent {
    String getLotName();
    double getSalePrice();
    double getTotalCost();
    List<String> getServiceDescriptions();
}
