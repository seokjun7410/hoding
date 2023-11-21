/**
 * @Class 관람객
 * @Dpenceny {@link Bag}
 */
public class Audience {
    private Bag bag;

    public Audience(final Bag bag) {
        this.bag = bag;
    }

    public Bag getBag() {
        return bag;
    }

    public Long buy(final Ticket ticket) {
        if(bag.hasInvitation()){
            bag.setTicket(ticket);
            return 0L;
        }else {
            bag.setTicket(ticket);
            bag.minusAmount(ticket.getFee());
            return ticket.getFee();
        }
    }
}
