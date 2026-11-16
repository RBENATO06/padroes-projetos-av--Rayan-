import java.util.ArrayList;
import java.util.List;

public class BaseLotPurchase implements InvoiceComponent {
    private final String lotName;
    private final double salePrice;

    public BaseLotPurchase(AuctionLot lot) {
        if (!lot.isSold()) {
            throw new IllegalStateException("Fatura so pode ser gerada para lote vendido.");
        }
        this.lotName = lot.getName();
        this.salePrice = lot.getCurrentBid();
    }

    @Override
    public String getLotName() {
        return lotName;
    }

    @Override
    public double getSalePrice() {
        return salePrice;
    }

    @Override
    public double getTotalCost() {
        return salePrice;
    }

    @Override
    public List<String> getServiceDescriptions() {
        return new ArrayList<>();
    }
}
