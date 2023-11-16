/**
 * @Class 판매원
 * @Dependency {@link TicketOffice} - 매표소
 */
public class TicketSeller {
    private TicketOffice ticketOffice;

    public TicketSeller(final TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    public TicketOffice getTicketOffice() {
        return ticketOffice;
    }
}
