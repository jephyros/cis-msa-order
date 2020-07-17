package kr.chis.cismsaorder.shop.event;

import kr.chis.cismsaorder.shop.domain.Ticket;
import org.springframework.context.ApplicationEvent;

/**
 * @author InSeok
 * Date : 2020/07/18
 * Remark : 티켓이 발행 완료됬을 때 생성되는 이벤트
 *
 */
public class TicketOpenedEvent extends ApplicationEvent {
    private Ticket ticket;
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public TicketOpenedEvent(Object source) {
        super(source);
        this.ticket  = (Ticket) source;
    }
    public Long getTicketId(){
        return ticket.getId();
    }
    public Long getOrderId(){
        return ticket.getOrderId();
    }
}
