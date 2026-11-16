public class AwsMessageQueue implements MessageQueue {
    private final String id;
    private final double monthlyFixedCost;

    public AwsMessageQueue(int sequence) {
        this.id = "sqs-" + sequence;
        this.monthlyFixedCost = 0.40;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getMonthlyFixedCost() {
        return monthlyFixedCost;
    }
}
