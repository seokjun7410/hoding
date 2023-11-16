import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Class 매표소
 * @Field amount - 돈,
 * @Dependency List {@link Ticket} - 티켓
 */
public class TicketOffice {
    private Long amount;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketOffice(final Long amount, final Ticket ...tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    public Ticket getTicket(){
        return tickets.remove(0);
    }

    public void minusAmount(Long amount){
        this.amount -= amount;
    }

    public void plusAmount(Long amount){
        this.amount += amount;
    }
}
