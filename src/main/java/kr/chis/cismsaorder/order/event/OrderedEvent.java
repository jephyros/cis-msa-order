package kr.chis.cismsaorder.order.event;

import kr.chis.cismsaorder.order.domain.Order;
import org.springframework.context.ApplicationEvent;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */

public class OrderedEvent extends ApplicationEvent {
    private Order order;
    private Long eventTimestamp;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public OrderedEvent(Object source) {
        super(source);
        this.order = (Order) source;
        this.eventTimestamp = System.currentTimeMillis();
    }

    public Long getOrderId(){
        return order.getId();
    }
    public String getOrderName(){
        return order.getOrderName();
    }
    public Long getEventTimestamp() { return this.eventTimestamp;}
}
