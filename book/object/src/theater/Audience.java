package theater;

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
        //bag은 수동적인 존재가 아니다. bag에게 자율성을 보장해주자
        //중요한것은 bag의 세부메소드의 의존하지 않게 하는 것이다.
        return bag.hold(ticket);
//        if(bag.hasInvitation()){
//            bag.setTicket(ticket);
//            return 0L;
//        }else {
//            bag.setTicket(ticket);
//            bag.minusAmount(ticket.getFee());
//            return ticket.getFee();
//        }
    }
}
