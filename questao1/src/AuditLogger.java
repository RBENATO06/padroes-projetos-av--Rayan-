import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuditLogger {
    private static volatile AuditLogger instance;
    private final List<AuditRecord> records;

    private AuditLogger() {
        this.records = new ArrayList<>();
    }

    public static AuditLogger getInstance() {
        if (instance == null) {
            synchronized (AuditLogger.class) {
                if (instance == null) {
                    instance = new AuditLogger();
                }
            }
        }
        return instance;
    }

    public synchronized void log(String environmentName, String providerName, String result, String reason) {
        records.add(new AuditRecord(LocalDateTime.now(), environmentName, providerName, result, reason));
    }

    public synchronized void printHistory() {
        System.out.println("=== HISTORICO DE AUDITORIA ===");
        for (AuditRecord record : records) {
            System.out.println(record);
        }
    }
}
