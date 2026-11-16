public class Main {
    public static void main(String[] args) throws InterruptedException {
        ProvisionRequest req1 = new ProvisionRequest("producao-api", "Aws", 2, 500);
        ProvisionRequest req2 = new ProvisionRequest("homologacao-web", "Aws", 1, 1000);
        ProvisionRequest req3 = new ProvisionRequest("dados-analytics", "Azure", 3, 200);
        ProvisionRequest req4 = new ProvisionRequest("cluster-pesado", "Azure", 8, 1000);

        Thread t1 = new Thread(() -> {
            CloudProviderFactory factory = CloudProviderSelector.select(req1.getProviderName());
            new EnvironmentProvisioner(factory).provision(req1);
        });

        Thread t2 = new Thread(() -> {
            CloudProviderFactory factory = CloudProviderSelector.select(req2.getProviderName());
            new EnvironmentProvisioner(factory).provision(req2);
        });

        t1.start();
        t2.start();

        CloudProviderFactory azureFactory3 = CloudProviderSelector.select(req3.getProviderName());
        new EnvironmentProvisioner(azureFactory3).provision(req3);

        CloudProviderFactory azureFactory4 = CloudProviderSelector.select(req4.getProviderName());
        new EnvironmentProvisioner(azureFactory4).provision(req4);

        t1.join();
        t2.join();

        AuditLogger.getInstance().printHistory();
    }
}
