public class AzureObjectStorage implements ObjectStorage {
    private final String id;
    private final double costPerGbMonth;

    public AzureObjectStorage(int sequence) {
        this.id = "blob://" + sequence;
        this.costPerGbMonth = 0.02;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getCostPerGbMonth() {
        return costPerGbMonth;
    }
}
