import java.util.ArrayList;
import java.util.List;

public class EnvironmentProvisioner {
    private static final int HOURS_PER_MONTH = 730;
    private final CloudProviderFactory factory;

    public EnvironmentProvisioner(CloudProviderFactory factory) {
        this.factory = factory;
    }

    public void provision(ProvisionRequest request) {
        ConfigurationManager config = ConfigurationManager.getInstance();
        AuditLogger logger = AuditLogger.getInstance();

        double estimatedCost = (request.getVmCount() * factory.getVmHourlyCost() * HOURS_PER_MONTH)
                + (request.getStorageGb() * factory.getStorageCostPerGbMonth())
                + factory.getQueueMonthlyFixedCost();

        if (estimatedCost > config.getMaxMonthlyBudget()) {
            String reason = String.format("Custo estimado R$ %.2f excede orcamento maximo R$ %.2f",
                    estimatedCost, config.getMaxMonthlyBudget());
            logger.log(request.getEnvironmentName(), factory.getProviderName(), "RECUSADO", reason);
            synchronized (System.out) {
                System.out.printf("Provisionamento RECUSADO para ambiente '%s' (%s): %s%n%n",
                        request.getEnvironmentName(), factory.getProviderName(), reason);
            }
            return;
        }

        List<VirtualMachine> vms = new ArrayList<>();
        for (int i = 0; i < request.getVmCount(); i++) {
            vms.add(factory.createVirtualMachine());
        }
        ObjectStorage storage = factory.createObjectStorage();
        MessageQueue queue = factory.createMessageQueue();

        logger.log(request.getEnvironmentName(), factory.getProviderName(), "ACEITO", "");

        synchronized (System.out) {
            System.out.println("=== RESUMO DO PROVISIONAMENTO ===");
            System.out.println("Ambiente: " + request.getEnvironmentName());
            System.out.println("Provedor: " + factory.getProviderName());
            System.out.println("Regiao: " + config.getDefaultRegion());
            System.out.print("Maquinas Virtuais: ");
            for (int i = 0; i < vms.size(); i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(vms.get(i).getId());
            }
            System.out.println();
            System.out.println("Armazenamento: " + storage.getId());
            System.out.println("Fila de Mensagens: " + queue.getId());
            System.out.printf("Custo Mensal Estimado: R$ %.2f%n%n", estimatedCost);
        }
    }
}
