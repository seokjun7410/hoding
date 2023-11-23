package theater;

/**
 * @Class 판매원
 * @Dependency {@link TicketOffice} - 매표소
 */
public class TicketSeller {
    private TicketOffice ticketOffice;
    private TicketSeller ticketSeller;

    public TicketSeller(final TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

//    public theater.TicketOffice getTicketOffice() {
//        return ticketOffice;
//    }

    public void sellTo(Audience audience){
        //ticketoffice의 자율성을 보장하자 목적에 맞는 추상화된 메소드가 필요하다.
        ticketOffice.sellTicketTo(audience);
        //ticketOffice.plusAmount(audience.buy(ticketOffice.getTicket()));

        //TicketSeller가 audience의 bag존재를 아는 것은 세부사항에 의존하는 것임으로 해당 로직을 Audience객체에 위임한다.
//        if(audience.getBag().hasInvitation()){
//            theater.Ticket ticket = ticketOffice.getTicket();
//            audience.getBag().setTicket(ticket);
//        }else {
//            theater.Ticket ticket = ticketOffice.getTicket();
//            audience.getBag().minusAmount(ticket.getFee());
//            ticketOffice.plusAmount(ticket.getFee());
//            audience.getBag().setTicket(ticket);
//        }
    }
}
