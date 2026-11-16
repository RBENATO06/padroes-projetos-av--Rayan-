public class AzureMessageQueue implements MessageQueue {
    private final String id;
    private final double monthlyFixedCost;

    public AzureMessageQueue(int sequence) {
        this.id = "sb-" + sequence;
        this.monthlyFixedCost = 0.50;
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
