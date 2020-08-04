package kr.chis.cismsaorder.shop.event;

import kr.chis.cismsaorder.order.event.OrderCanceledEvent;
import kr.chis.cismsaorder.order.event.OrderedEvent;
import kr.chis.cismsaorder.shop.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark : 가계서비스로 전달되는 이벤트리스너
 */

@Component
public class ShopEventListener {

    private final TicketService ticketService;

    @Autowired
    public ShopEventListener(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @EventListener
    public void orderedEventhandle(OrderedEvent event) {
        //오더등록이벤트수신
        System.out.println("OrderedCreateEvent=====" + event.getOrderName());
        // 1. Ticket 을 발행
        ticketService.create(event.getOrderId());
        // 2. 티켓발행 이벤트 발행
    }

    @EventListener
    public void orderCanceledEventhandle(OrderCanceledEvent event) {
        System.out.println("OrderedCancel=====" + event.getOrderName());

    }
}
