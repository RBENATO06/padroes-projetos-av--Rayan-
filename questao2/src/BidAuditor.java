import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BidAuditor implements AuctionObserver {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final List<String> logs = new ArrayList<>();

    @Override
    public void update(AuctionLot lot, Bid bid) {
        String entry = String.format("[%s] Lote: %s | Participante: %s | Valor: R$ %.2f",
                bid.getTimestamp().format(FORMATTER),
                lot.getName(),
                bid.getParticipant().getName(),
                bid.getAmount());
        logs.add(entry);
        System.out.println("[Auditor] Registro -> " + entry);
    }

    public List<String> getLogs() {
        return Collections.unmodifiableList(logs);
    }
}
