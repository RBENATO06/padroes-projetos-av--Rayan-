public class ProvisionRequest {
    private final String environmentName;
    private final String providerName;
    private final int vmCount;
    private final int storageGb;

    public ProvisionRequest(String environmentName, String providerName, int vmCount, int storageGb) {
        this.environmentName = environmentName;
        this.providerName = providerName;
        this.vmCount = vmCount;
        this.storageGb = storageGb;
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public String getProviderName() {
        return providerName;
    }

    public int getVmCount() {
        return vmCount;
    }

    public int getStorageGb() {
        return storageGb;
    }
}
