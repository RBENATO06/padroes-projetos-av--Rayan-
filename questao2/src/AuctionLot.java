import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuctionLot implements AuctionSubject {
    private final String name;
    private final String category;
    private final double initialPrice;
    private final double reservePrice;
    private double currentBid;
    private boolean closed;
    private boolean sold;
    private Participant highestBidder;
    private final List<Bid> bids;
    private final List<AuctionObserver> observers;
    private CommissionStrategy commissionStrategy;

    public AuctionLot(String name, String category, double initialPrice, double reservePrice,
                      CommissionStrategy commissionStrategy) {
        this.name = name;
        this.category = category;
        this.initialPrice = initialPrice;
        this.reservePrice = reservePrice;
        this.currentBid = initialPrice;
        this.closed = false;
        this.sold = false;
        this.bids = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.commissionStrategy = commissionStrategy;
    }

    @Override
    public void addObserver(AuctionObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(AuctionObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Bid bid) {
        for (AuctionObserver observer : new ArrayList<>(observers)) {
            observer.update(this, bid);
        }
    }

    public boolean placeBid(Participant participant, double amount) {
        if (closed) {
            System.out.printf("Lance recusado para '%s': o lote ja esta encerrado.%n", name);
            return false;
        }
        double minimumRequired = currentBid * 1.05;
        if (amount + 1e-6 < minimumRequired) {
            System.out.printf("Lance de R$ %.2f por %s recusado para '%s': valor deve ser pelo menos 5%% maior que R$ %.2f (minimo R$ %.2f).%n",
                    amount, participant.getName(), name, currentBid, minimumRequired);
            return false;
        }
        Bid bid = new Bid(participant, amount);
        this.currentBid = amount;
        this.highestBidder = participant;
        this.bids.add(bid);
        System.out.printf("Lance aceito em '%s': %s ofertou R$ %.2f%n", name, participant.getName(), amount);
        notifyObservers(bid);
        return true;
    }

    public void setCommissionStrategy(CommissionStrategy commissionStrategy) {
        if (closed) {
            throw new IllegalStateException("Nao e possivel alterar a regra de comissao de um lote encerrado.");
        }
        this.commissionStrategy = commissionStrategy;
    }

    public void close() {
        if (closed) {
            return;
        }
        this.closed = true;
        if (highestBidder != null && currentBid + 1e-6 >= reservePrice) {
            this.sold = true;
            System.out.printf("Lote '%s' ENCERRADO: VENDIDO para %s por R$ %.2f.%n",
                    name, highestBidder.getName(), currentBid);
        } else {
            this.sold = false;
            System.out.printf("Lote '%s' ENCERRADO: NAO VENDIDO (maior lance R$ %.2f abaixo da reserva R$ %.2f).%n",
                    name, currentBid, reservePrice);
        }
    }

    public double calculateCommission() {
        if (!sold) {
            return 0.0;
        }
        return commissionStrategy.calculate(currentBid);
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public double getReservePrice() {
        return reservePrice;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean isSold() {
        return sold;
    }

    public Participant getHighestBidder() {
        return highestBidder;
    }

    public List<Bid> getBids() {
        return Collections.unmodifiableList(bids);
    }
}
