import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigurationManager {
    private static volatile ConfigurationManager instance;
    private final String defaultRegion;
    private final double maxMonthlyBudget;

    private ConfigurationManager() {
        Properties props = new Properties();
        try (InputStream input = openConfigStream()) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar config.properties", e);
        }
        this.defaultRegion = props.getProperty("regiao.padrao", "sa-east-1").trim();
        this.maxMonthlyBudget = Double.parseDouble(props.getProperty("orcamento.mensal.maximo", "500.00").trim());
    }

    private InputStream openConfigStream() throws IOException {
        Path[] candidates = {
            Paths.get("config.properties"),
            Paths.get("src", "config.properties"),
            Paths.get("questao1", "src", "config.properties")
        };
        for (Path path : candidates) {
            if (Files.exists(path)) {
                return new FileInputStream(path.toFile());
            }
        }
        InputStream resourceStream = ConfigurationManager.class.getResourceAsStream("/config.properties");
        if (resourceStream != null) {
            return resourceStream;
        }
        throw new IOException("Arquivo config.properties nao encontrado");
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    public String getDefaultRegion() {
        return defaultRegion;
    }

    public double getMaxMonthlyBudget() {
        return maxMonthlyBudget;
    }
}
