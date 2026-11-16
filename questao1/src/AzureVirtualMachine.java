public class AzureVirtualMachine implements VirtualMachine {
    private final String id;
    private final double hourlyCost;

    public AzureVirtualMachine(int sequence) {
        this.id = "vm-" + sequence;
        this.hourlyCost = 0.12;
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
