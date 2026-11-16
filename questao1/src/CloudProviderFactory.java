public interface CloudProviderFactory {
    String getProviderName();
    VirtualMachine createVirtualMachine();
    ObjectStorage createObjectStorage();
    MessageQueue createMessageQueue();
    double getVmHourlyCost();
    double getStorageCostPerGbMonth();
    double getQueueMonthlyFixedCost();
}
