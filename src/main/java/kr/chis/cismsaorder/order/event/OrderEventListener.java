package kr.chis.cismsaorder.order.event;

import kr.chis.cismsaorder.order.service.OrderService;
import kr.chis.cismsaorder.shop.event.TicketOpenedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author InSeok
 * Date : 2020-07-16
 * Remark : 주문서비스로 수신되는 이벤트 리스터
 */
@Slf4j
@Component
public class OrderEventListener {
    private final OrderService orderService;

    public OrderEventListener(OrderService orderService) {
        this.orderService = orderService;
    }

    //샵에서 티켓 오픈되는 이벤트수신하여 오더 상태변경 (OrderStatus.ORDER_PENGIND ->ORDER_ACCEPT )
    @EventListener
    public void ticketOpenedHandle(TicketOpenedEvent event){
        log.info("샵 티켓 오픈 이벤트 수신 :'" + event.getOrderId()+ "'");
        orderService.orderAccept(event.getOrderId());
    }
}
