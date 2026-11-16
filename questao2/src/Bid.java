import java.time.LocalDateTime;

public class Bid {
    private final Participant participant;
    private final double amount;
    private final LocalDateTime timestamp;

    public Bid(Participant participant, double amount) {
        this.participant = participant;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public Participant getParticipant() {
        return participant;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
