import java.util.concurrent.atomic.AtomicInteger;

public class AzureFactory implements CloudProviderFactory {
    private final AtomicInteger vmCounter = new AtomicInteger(0);
    private final AtomicInteger storageCounter = new AtomicInteger(0);
    private final AtomicInteger queueCounter = new AtomicInteger(0);

    @Override
    public String getProviderName() {
        return "Azure";
    }

    @Override
    public VirtualMachine createVirtualMachine() {
        return new AzureVirtualMachine(vmCounter.incrementAndGet());
    }

    @Override
    public ObjectStorage createObjectStorage() {
        return new AzureObjectStorage(storageCounter.incrementAndGet());
    }

    @Override
    public MessageQueue createMessageQueue() {
        return new AzureMessageQueue(queueCounter.incrementAndGet());
    }

    @Override
    public double getVmHourlyCost() {
        return 0.12;
    }

    @Override
    public double getStorageCostPerGbMonth() {
        return 0.02;
    }

    @Override
    public double getQueueMonthlyFixedCost() {
        return 0.50;
    }
}
