public class AwsVirtualMachine implements VirtualMachine {
    private final String id;
    private final double hourlyCost;

    public AwsVirtualMachine(int sequence) {
        this.id = "i-" + sequence;
        this.hourlyCost = 0.10;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getHourlyCost() {
        return hourlyCost;
    }
}
