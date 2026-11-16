public class Participant implements AuctionObserver {
    private final String name;

    public Participant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(AuctionLot lot, Bid bid) {
        if (!bid.getParticipant().equals(this)) {
            System.out.printf("[Notificacao para %s] Lote '%s' recebeu novo lance de R$ %.2f%n",
                    name, lot.getName(), bid.getAmount());
        }
    }
}
