import java.util.HashMap;
import java.util.Map;

public class AuctioneerPanel implements AuctionObserver {
    private final Map<String, Double> highestBids = new HashMap<>();
    private final Map<String, Integer> bidCounts = new HashMap<>();

    @Override
    public void update(AuctionLot lot, Bid bid) {
        String lotName = lot.getName();
        double currentMax = highestBids.getOrDefault(lotName, 0.0);
        if (bid.getAmount() > currentMax) {
            highestBids.put(lotName, bid.getAmount());
        }
        int total = bidCounts.getOrDefault(lotName, 0) + 1;
        bidCounts.put(lotName, total);
        System.out.printf("[Painel do Leiloeiro] Lote '%s' | Maior lance: R$ %.2f | Total de lances: %d%n",
                lotName, highestBids.get(lotName), total);
    }
}
