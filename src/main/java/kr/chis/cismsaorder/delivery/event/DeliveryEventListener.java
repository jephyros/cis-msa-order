package kr.chis.cismsaorder.delivery.event;

import kr.chis.cismsaorder.order.event.OrderCanceledEvent;
import kr.chis.cismsaorder.order.event.OrderedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark : 배달서비스로 전달되는 이벤트리스너
 */

@Component
public class DeliveryEventListener {
    @EventListener
    public void orderedEventhandle(OrderedEvent event) {
        System.out.println("OrderedEvent=====" + event.getOrderName());

    }

    @EventListener
    public void orderCanceledEventhandle(OrderCanceledEvent event) {
        System.out.println("OrderedCancel=====" + event.getOrderName());

    }
}
