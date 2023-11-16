
/**
 * @class 가방
 * @field amount - 현금
 * @Dependency {@link Invitation} - 초대장
 * @Dependency {@link Ticket} - 티켓
 */
public class Bag {
    private Long amount;
    private Invitation invitation;
    private Ticket ticket;


    //초기 가방에 소지품이 존재할 수 있는 case => 생성자로 구성
    public Bag(final long amount){
        this(amount,null);
    }
    public Bag(final Long amount, final Invitation invitation) {
        this.amount = amount;
        this.invitation = invitation;
    }

    public boolean hasInvitation(){
        return invitation != null;
    }

    public boolean hasTicket(){
        return ticket != null;
    }

    public void setTicket(final Ticket ticket) {
        this.ticket = ticket;
    }

    public void minusAmount(Long amount){
        this.amount -= amount;
    }

    public void plusAmount(Long amount){
        this.amount += amount;
    }


}
