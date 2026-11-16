import java.util.concurrent.atomic.AtomicInteger;

public class AwsFactory implements CloudProviderFactory {
    private final AtomicInteger vmCounter = new AtomicInteger(0);
    private final AtomicInteger storageCounter = new AtomicInteger(0);
    private final AtomicInteger queueCounter = new AtomicInteger(0);

    @Override
    public String getProviderName() {
        return "Aws";
    }

    @Override
    public VirtualMachine createVirtualMachine() {
        return new AwsVirtualMachine(vmCounter.incrementAndGet());
    }

    @Override
    public ObjectStorage createObjectStorage() {
        return new AwsObjectStorage(storageCounter.incrementAndGet());
    }

    @Override
    public MessageQueue createMessageQueue() {
        return new AwsMessageQueue(queueCounter.incrementAndGet());
    }

    @Override
    public double getVmHourlyCost() {
        return 0.10;
    }

    @Override
    public double getStorageCostPerGbMonth() {
        return 0.023;
    }

    @Override
    public double getQueueMonthlyFixedCost() {
        return 0.40;
    }
}
