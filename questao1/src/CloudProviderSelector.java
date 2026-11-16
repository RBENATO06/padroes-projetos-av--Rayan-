import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CloudProviderSelector {
    private static final Map<String, CloudProviderFactory> FACTORIES = new ConcurrentHashMap<>();

    static {
        FACTORIES.put("AWS", new AwsFactory());
        FACTORIES.put("AZURE", new AzureFactory());
    }

    public static CloudProviderFactory select(String providerName) {
        if (providerName == null) {
            throw new IllegalArgumentException("Provedor nao pode ser nulo");
        }
        CloudProviderFactory factory = FACTORIES.get(providerName.trim().toUpperCase());
        if (factory == null) {
            throw new IllegalArgumentException("Provedor desconhecido: " + providerName);
        }
        return factory;
    }
}
