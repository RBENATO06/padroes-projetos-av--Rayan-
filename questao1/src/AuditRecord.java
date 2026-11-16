import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditRecord {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private final LocalDateTime timestamp;
    private final String environmentName;
    private final String providerName;
    private final String result;
    private final String reason;

    public AuditRecord(LocalDateTime timestamp, String environmentName, String providerName, String result, String reason) {
        this.timestamp = timestamp;
        this.environmentName = environmentName;
        this.providerName = providerName;
        this.result = result;
        this.reason = reason;
    }

    @Override
    public String toString() {
        String base = String.format("[%s] Ambiente: %s | Provedor: %s | Resultado: %s",
                timestamp.format(FORMATTER), environmentName, providerName, result);
        if (reason != null && !reason.isEmpty()) {
            return base + " | Motivo: " + reason;
        }
        return base;
    }
}
