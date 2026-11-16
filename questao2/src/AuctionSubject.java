public interface AuctionSubject {
    void addObserver(AuctionObserver observer);
    void removeObserver(AuctionObserver observer);
    void notifyObservers(Bid bid);
}
