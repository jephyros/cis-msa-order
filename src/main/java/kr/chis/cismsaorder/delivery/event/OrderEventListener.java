package kr.chis.cismsaorder.delivery.event;

import kr.chis.cismsaorder.order.event.OrderSaveEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */

@Component
public class OrderEventListener {
    @EventListener
    public void handle(OrderSaveEvent event) {
        System.out.println("================" + event.getOrderName());

    }
}
