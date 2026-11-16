public class AwsObjectStorage implements ObjectStorage {
    private final String id;
    private final double costPerGbMonth;

    public AwsObjectStorage(int sequence) {
        this.id = "s3://" + sequence;
        this.costPerGbMonth = 0.023;
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
